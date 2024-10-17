package com.hoaiphong.carrental.controllers;

import java.util.List;

import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.hoaiphong.carrental.entities.Car;
import com.hoaiphong.carrental.entities.CarBooking;
import com.hoaiphong.carrental.services.CarBookingService;

@Controller
public class SearchController {

    private final CarBookingService carBookingService;

    public SearchController(CarBookingService carBookingService) {
        this.carBookingService = carBookingService;
    }
    @GetMapping("search")
public String search(Model model,
        @RequestParam(defaultValue = "car.name") String sort,
        @RequestParam(defaultValue = "asc") String order,
        @RequestParam(defaultValue = "0") int page, // Page Index - Trang thứ bao nhiêu
        @RequestParam(defaultValue = "2") int size,
        @RequestParam(required = false) String pickupLocation) {

    Sort.Direction direction = Sort.Direction.fromString(order); // Convert string to Direction
    var pageable = PageRequest.of(page, size, Sort.by(direction, sort));

    // Khai báo biến carBookingsFind ở bên ngoài if/else
    Object carBookingsFind;

    if (pickupLocation == null || pickupLocation.trim().isEmpty()) {
        // Nếu pickupLocation rỗng hoặc null, tìm tất cả xe
        carBookingsFind = carBookingService.findAll();
    } else {
        // Nếu có pickupLocation, tìm xe theo location và phân trang
        carBookingsFind = carBookingService.searchByAddress(pickupLocation, pageable);
    }

    // Thêm các tham số vào model
    model.addAttribute("cars", carBookingsFind);
    model.addAttribute("sort", sort);  // Không cần kiểm tra null vì đã có defaultValue
    model.addAttribute("order", order);  // Không cần kiểm tra null vì đã có defaultValue
    model.addAttribute("pickupLocation", pickupLocation == null ? "" : pickupLocation);
    model.addAttribute("currentPage", page);
    model.addAttribute("pageSize", size);

    // Kiểm tra loại của carBookingsFind, nếu là Page<CarBooking> thì lấy totalPages và totalElements
    if (carBookingsFind instanceof Page) {
        // Nếu là Page<CarBooking>, lấy các thông tin phân trang
        Page<CarBooking> pageCars = (Page<CarBooking>) carBookingsFind;
        model.addAttribute("totalPages", pageCars.getTotalPages());
        model.addAttribute("totalElements", pageCars.getTotalElements());
    } else if (carBookingsFind instanceof List) {
        // Nếu là List<CarBooking>, lấy size() cho totalElements và set totalPages = 1
        List<CarBooking> listCars = (List<CarBooking>) carBookingsFind;
        model.addAttribute("totalPages", 1); // Vì không phân trang nên chỉ có 1 trang
        model.addAttribute("totalElements", listCars.size());
    } else {
        // Nếu không phải Page cũng không phải List, mặc định là 0
        model.addAttribute("totalPages", 0);
        model.addAttribute("totalElements", 0);
    }

    model.addAttribute("pageSizes", new int[] { 2, 5, 10, 20 });

    return "SearchAndBook/search";
}

  
    
@GetMapping("/detail/{carId}")
public String detail(Model model, @PathVariable UUID carId) {
    var car = carBookingService.findByCarId(carId);
    model.addAttribute("carBooking", car);
    return "SearchAndBook/detail";
}

@GetMapping("/listCar")
public String listCar(Model model) {
    model.addAttribute("car", new Car());
    return "SearchAndBook/listCar";
}
}
