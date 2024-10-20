package com.hoaiphong.carrental.controllers;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hoaiphong.carrental.entities.Booking;
import com.hoaiphong.carrental.entities.Car;
import com.hoaiphong.carrental.entities.CarBooking;
import com.hoaiphong.carrental.entities.CarBookingId;
import com.hoaiphong.carrental.entities.User;
import com.hoaiphong.carrental.repositories.UserRepository;
import com.hoaiphong.carrental.services.BookingService;
import com.hoaiphong.carrental.services.CarBookingService;
import com.hoaiphong.carrental.services.CarService;

import jakarta.servlet.http.HttpSession;

@Controller
public class SearchController {

    private final UserRepository userService;

    private final CarBookingService carBookingService;
    private final CarService carService;
    private final BookingService bookingService;

    public SearchController(CarBookingService carBookingService, CarService carService, BookingService bookingService,
            UserRepository userService) {
        this.userService = userService;
        this.carBookingService = carBookingService;
        this.carService = carService;
        this.bookingService = bookingService;
    }

    @GetMapping("search")
    public String search(Model model,
            @RequestParam(defaultValue = "name") String sort,
            @RequestParam(defaultValue = "asc") String order,
            @RequestParam(defaultValue = "0") int page, // Page Index - Trang thứ bao nhiêu
            @RequestParam(defaultValue = "2") int size,
            @RequestParam(required = false) String pickupLocation) {

        Sort.Direction direction = Sort.Direction.fromString(order); // Convert string to Direction
        var pageable = PageRequest.of(page, size, Sort.by(direction, sort));

        // Khai báo biến carBookingsFind ở bên ngoài if/else
        var cars = carService.search(pickupLocation, pageable);
        model.addAttribute("cars", cars);

        // Pass current keyword to view to show in keyword input of search form
        model.addAttribute("keyword", pickupLocation == null ? "" : pickupLocation);

        // Pass current page to view
        model.addAttribute("currentPage", page);

        // Pass current sort to view
        model.addAttribute("sort", sort == null ? "name" : sort);

        // Pass current direction to view
        model.addAttribute("order", order == null ? "asc" : order);

        // Pass current page size to viewa
        model.addAttribute("pageSize", size);

        // Using pagination
        model.addAttribute("totalPages", cars.getTotalPages());
        model.addAttribute("totalElements", cars.getTotalElements());
        model.addAttribute("pageSizes", new int[]{2, 5, 10, 20});

        return "SearchAndBook/search";
    }

    @GetMapping("/detail/{carId}")
    public String detail(Model model, @PathVariable UUID carId) {
        var Car = carService.findById(carId);

        model.addAttribute("car", Car);

        return "SearchAndBook/detail";
    }

    @GetMapping("/bookingInformation/{carId}")
    public String bookingInformation(
            @PathVariable("carId") UUID carId,
            @RequestParam("startDateTime") String startDateTime,
            @RequestParam("endDateTime") String endDateTime,
            Model model) {

        // Chuyển đổi startDateTime và endDateTime từ String thành LocalDateTime
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        LocalDateTime start = LocalDateTime.parse(startDateTime, formatter);
        LocalDateTime end = LocalDateTime.parse(endDateTime, formatter);

        // Tìm thông tin carBooking theo carId
        var car = carService.findById(carId);
        // Tính số ngày giữa startDate và endDate
        long daysBetween = Duration.between(start, end).toDays();
        double basePrice = car.getBasePrice();
        // Thêm thông tin vào model
        model.addAttribute("car", car);
        model.addAttribute("startDateTime", start);
        model.addAttribute("endDateTime", end);

        double totalPrice = basePrice * daysBetween;

        model.addAttribute("carId", carId);

        // Truyền startDateTime, endDateTime vào hidden input để gửi qua form
        model.addAttribute("startDateTimeStr", startDateTime);
        model.addAttribute("endDateTimeStr", endDateTime);

        CarBooking carBooking = new CarBooking();
        carBooking.setTotalPrice(totalPrice); // Lưu giá trị totalPrice dưới dạng double
        model.addAttribute("carBooking", carBooking);
        model.addAttribute("basePrice", car.getBasePrice());
        model.addAttribute("totalPrice", totalPrice); // Hiển thị giá trị đã định dạng
        model.addAttribute("daysBetween", daysBetween);

        return "searchAndBook/bookingInformation";

    }

    @PostMapping("/bookingPayment")
    public String bookingInformation1(HttpSession session, @ModelAttribute("carBooking") CarBooking carBooking, @ModelAttribute("carId") UUID carId,
            @ModelAttribute("startDateTime") LocalDateTime startDateTime,
            @ModelAttribute("endDateTime") LocalDateTime endDateTime,
            @ModelAttribute("totalPrice") double totalPrice,
            @ModelAttribute("daysBetween") double daysBetween,
            Model model, @AuthenticationPrincipal UserDetails userDetails) {

        User user = userService.findByEmail(userDetails.getUsername());
        Car car = carService.findById(carId);
        Booking booking = new Booking();
        booking.setEndDate(endDateTime);
        booking.setStartDate(startDateTime);
        booking.setUser(user);

        bookingService.save(booking);

        System.out.println(user.getWallet());
        CarBookingId carBookingId = new CarBookingId();
        carBookingId.setCarId(carId);
        carBookingId.setBookingId(booking.getId());
        carBooking.setCarBookingId(carBookingId);
        carBooking.setCar(car); // Set đối tượng car nếu cần thiết
        carBooking.setBooking(booking); // Set đối tượng booking nếu cần thiết
        carBooking.setStatus("in-progress");
        carBookingService.save(carBooking);
        model.addAttribute("wallet", user.getWallet());
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("daysBetween", daysBetween);
        System.out.println(carBooking.getRenterFullName());
        session.setAttribute("carBooking", carBooking);
        model.addAttribute("carBooking", carBooking);

        return "SearchAndBook/bookingPayment";
    }

    @PostMapping("/bookingFinish")
    public String bookingFinish(@ModelAttribute("totalPrice") double totalPrice,
            @ModelAttribute("daysBetween") double daysBetween,
            Model model,
            HttpSession session,
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam(required = false) String paymentMethod) {
        CarBooking carBooking = (CarBooking) session.getAttribute("carBooking");

        // Kiểm tra nếu carBooking là null
        if (carBooking == null) {
            model.addAttribute("errorMessage", "No booking found.");
            return "SearchAndBook/bookingPayment"; // Quay lại trang thanh toán với thông báo lỗi
        }

        User user = userService.findByEmail(userDetails.getUsername());
        double walletBalance = user.getWallet();

        // Kiểm tra nếu không chọn phương thức thanh toán
        if (paymentMethod == null || paymentMethod.isEmpty()) {
            model.addAttribute("errorMessage", "Please select a payment method."); // Thông báo lỗi
            return "SearchAndBook/bookingPayment"; // Quay lại trang thanh toán với thông báo lỗi
        }

        // Kiểm tra phương thức thanh toán
        if ("wallets".equals(paymentMethod)) {
            // Kiểm tra số dư ví
            if (walletBalance < totalPrice) {
                model.addAttribute("errorMessage", "Insufficient wallet balance.");
                carBooking.setStatus("pending deposit");
                return "SearchAndBook/bookingPayment"; // Quay lại trang thanh toán với thông báo lỗi
            }
            // Trừ số tiền từ ví
            user.setWallet(walletBalance - totalPrice);
            userService.save(user); // Cập nhật lại số dư ví trong cơ sở dữ liệu
        }

        // Lưu phương thức thanh toán vào carBooking
        carBooking.setPaymentMethod(paymentMethod);
        carBooking.setTotalPrice(totalPrice); // Nếu bạn có thuộc tính totalPrice trong CarBooking
        carBooking.setStatus("Confirm");// Lưu carBooking vào cơ sở dữ liệu
        carBookingService.save(carBooking);
        // Nếu tất cả điều kiện thỏa mãn
        model.addAttribute("daysBetween", daysBetween);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("carBooking", carBooking);
        session.setAttribute("carBooking", carBooking);
        return "SearchAndBook/bookingFinish";
    }

    @GetMapping("viewBookingDetail")
    public String viewBookingDetail(Model model, HttpSession session,
            @AuthenticationPrincipal UserDetails userDetails) {
                CarBooking carBooking = (CarBooking) session.getAttribute("carBooking");
            Booking booking = carBooking.getBooking();
            var start = booking.getStartDate();
            var end = booking.getEndDate();

          
        
                long daysBetween = Duration.between(start, end).toDays();
                double basePrice = carBooking.getCar().getBasePrice();
                double totalPrice = basePrice * daysBetween;
        User user = userService.findByEmail(userDetails.getUsername());
        model.addAttribute("daysBetween", daysBetween);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("carBooking", carBooking);
        var paymentMethod = carBooking.getPaymentMethod();
        model.addAttribute("paymentMethod", paymentMethod);
        var wallet = user.getWallet();
        model.addAttribute("wallet", wallet);
        session.setAttribute("carBooking", carBooking);
        return "SearchAndBook/viewBookingDetail";
    }

    @PostMapping("viewBookingDetail")
    public String viewBookingDetail(HttpSession session, @ModelAttribute("carBooking") CarBooking carBooking, Model model) {
        CarBooking carBooking123 = (CarBooking) session.getAttribute("carBooking");

        carBooking123.setDriverDrivingLicense(carBooking.getDriverDrivingLicense());
        carBooking123.setRenterFullName(carBooking.getRenterFullName());
        carBooking123.setDriverEmail(carBooking.getDriverEmail());
        carBooking123.setDriverFullName(carBooking.getDriverFullName());
        carBooking123.setRenterEmail(carBooking.getRenterEmail());
        carBooking123.setRenterPhone(carBooking.getRenterPhone());
        carBooking123.setDriverStreet(carBooking.getDriverStreet());
        carBooking123.setDriverCity(carBooking.getDriverCity());
        carBooking123.setRenterStreet(carBooking.getRenterStreet());
        carBooking123.setRenterCity(carBooking.getRenterCity());
        carBooking123.setRenterDrivingLicense(carBooking.getRenterDrivingLicense());
        carBooking123.setRenterPhone(carBooking.getRenterPhone());
        carBooking123.setRenterEmail(carBooking.getRenterEmail());

        carBookingService.save(carBooking123);
        session.setAttribute("carBooking", carBooking123);
        model.addAttribute("carBooking", carBooking123);
        return "redirect:/viewBookingDetail";
    }

    @GetMapping("listBookingUser")
    public String listBookingUser(Model model,
            @RequestParam(defaultValue = "0") int page, // Page Index - Trang thứ bao nhiêu
            @RequestParam(defaultValue = "2") int size,
            @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByEmail(userDetails.getUsername());
        var pageable = PageRequest.of(page, size); // Chỉ phân trang mà không cần sắp xếp
        Page<CarBooking> carBookingPages = carBookingService.findByAll(user.getId(), pageable);

        model.addAttribute("carBookings", carBookingPages.getContent()); // Danh sách các CarBooking
        model.addAttribute("currentPage", page); // Trang hiện tại
        model.addAttribute("totalPages", carBookingPages.getTotalPages()); // Tổng số trang
        model.addAttribute("totalElements", carBookingPages.getTotalElements()); // Tổng số phần tử
        model.addAttribute("pageSize", size);
        model.addAttribute("pageSizes", new int[]{2, 5, 10, 20});
        // Khai báo biến cars với dữ liệu được phân trang
        return "SearchAndBook/listBookingUser";

    }
}
