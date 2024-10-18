package com.hoaiphong.carrental.controllers;

import java.time.temporal.ChronoUnit;
import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
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
        model.addAttribute("daysBetween", daysBetween);
        model.addAttribute("basePrice", car.getBasePrice());
        double totalPrice = basePrice * daysBetween;
        DecimalFormat df = new DecimalFormat("#,###");
        String formattedTotalPrice = df.format(totalPrice);
        model.addAttribute("totalPrice", formattedTotalPrice);
        model.addAttribute("carId", carId);

        // Truyền startDateTime, endDateTime vào hidden input để gửi qua form
        model.addAttribute("startDateTimeStr", startDateTime);
        model.addAttribute("endDateTimeStr", endDateTime);
        CarBooking carBooking = new CarBooking();
        model.addAttribute("carBooking", carBooking);

        return "searchAndBook/bookingInformation";
    }

    @PostMapping("/bookingPayment")
    public String bookingInformation1(@ModelAttribute("carBooking") CarBooking carBooking, @ModelAttribute("carId") UUID carId,
            @ModelAttribute("startDateTime") LocalDateTime startDateTime,
            @ModelAttribute("endDateTime") LocalDateTime endDateTime,
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

        carBookingService.save(carBooking);
        model.addAttribute("wallet", user.getWallet());
        model.addAttribute("carBooking", carBooking);
        System.out.println(carBooking.getRenterFullName());

        return "SearchAndBook/bookingPayment";
    }

    // @GetMapping("/bookingPayment")
    // public String bookingPayment(@ModelAttribute("carBooking") CarBooking carBooking, Model model) {
    //     System.out.println(carBooking.getRenterFullName());
    //     LocalDateTime start = carBooking.getBooking().getEndDate();
    //     LocalDateTime end = carBooking.getBooking().getStartDate();
    //     long daysBetween = ChronoUnit.DAYS.between(start, end);
    //     double basePrice = carBooking.getCar().getBasePrice();
    //     double totalPrice = basePrice * daysBetween;
    //     DecimalFormat df = new DecimalFormat("#,###");
    //     String formattedTotalPrice = df.format(totalPrice);
    //     model.addAttribute("totalPrice", formattedTotalPrice);

    //     return "SearchAndBook/bookingPayment";
    // }

}
