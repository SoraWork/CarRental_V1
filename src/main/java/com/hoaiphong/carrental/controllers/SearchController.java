package com.hoaiphong.carrental.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
            @RequestParam(defaultValue = "name") String sort,
            @RequestParam(defaultValue = "asc") String order,
            @RequestParam(defaultValue = "0") int page, // Page Index - Trang thứ bao nhiêu
            @RequestParam(defaultValue = "2") int size,
            @RequestParam(required = false) String pickupLocation,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String startTime,
            @RequestParam(required = false) String endDate,
            @RequestParam(required = false) String endTime) {

        Sort.Direction direction = Sort.Direction.fromString(order); // Convert string to Direction
        var pageable = PageRequest.of(page, size, Sort.by(direction, sort));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        LocalDateTime startDateTime = null;
        if (startDate != null && !startDate.isEmpty() && startTime != null && !startTime.isEmpty()) {
            String startDateTimeString = startDate + " " + startTime; // Chỉnh lại định dạng
            try {
                startDateTime = LocalDateTime.parse(startDateTimeString, formatter);
            } catch (DateTimeParseException e) {
                model.addAttribute("error", "Invalid start date or time format.");
                return "SearchAndBook/search";
            }
        }

        LocalDateTime endDateTime = null;
        if (endDate != null && !endDate.isEmpty() && endTime != null && !endTime.isEmpty()) {
            String endDateTimeString = endDate + " " + endTime; // Chỉnh lại định dạng
            try {
                endDateTime = LocalDateTime.parse(endDateTimeString, formatter);
            } catch (DateTimeParseException e) {
                model.addAttribute("error", "Invalid end date or time format.");
                return "SearchAndBook/search";
            }
        }
        if (pickupLocation == null) {
            List<CarBooking> carBookingsFind = carBookingService.findAll();
            model.addAttribute("cars", carBookingsFind);
            return "SearchAndBook/search";

        } else {
            Page<CarBooking> carBookingsFind = carBookingService.searchByAddress(pickupLocation, pageable);
            System.out.println(carBookingsFind);
            model.addAttribute("currentPage", page);
            model.addAttribute("sort", sort);
            model.addAttribute("order", order);
            model.addAttribute("pageSize", size);
            model.addAttribute("totalPages", carBookingsFind.getTotalPages());
            model.addAttribute("totalElements", carBookingsFind.getTotalElements());
            model.addAttribute("pageSizes", new int[] { 2, 5, 10, 20 });
            model.addAttribute("cars", carBookingsFind);

            return "SearchAndBook/search";

        }

    

    }

    @GetMapping("detail")
    public String detail(Model model) {
        model.addAttribute("car", new Car());
        return "SearchAndBook/detail";
    }

    @GetMapping("listCar")
    public String listCar(Model model) {
        model.addAttribute("car", new Car());
        return "SearchAndBook/listCar";
    }

    @GetMapping("book")
    public String book(Model model) {
        model.addAttribute("car", new Car());
        return "SearchAndBook/book";
    }

}
