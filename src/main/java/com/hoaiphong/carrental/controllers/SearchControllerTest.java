// package com.hoaiphong.carrental.controllers;

// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.PageRequest;
// import org.springframework.data.domain.Sort;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestParam;

// import com.hoaiphong.carrental.entities.Car;
// import com.hoaiphong.carrental.entities.CarBooking;
// import com.hoaiphong.carrental.services.CarBookingService;

// @Controller
// public class SearchControllerTest {

//     private final CarBookingService carBookingService;

//     public SearchController(CarBookingService carBookingService) {
//         this.carBookingService = carBookingService;
//     }

  
//    @GetMapping("/search")
// public String searchByAddress(
//         @RequestParam(required = false) String pickupLocation, 
//         @RequestParam(defaultValue = "0") int page, 
//         @RequestParam(defaultValue = "2") int size, 
//         @RequestParam(defaultValue = "car.name") String sort, 
//         @RequestParam(defaultValue = "asc") String order,
//         Model model) {

//     Sort.Direction direction = Sort.Direction.fromString(order); 
//     PageRequest pageable = PageRequest.of(page, size, Sort.by(direction, sort));
    
//     Page<CarBooking> carBookings = carBookingService.searchByAddress(pickupLocation, pageable);

//     model.addAttribute("cars", carBookings);
//     model.addAttribute("currentPage", page);
//     model.addAttribute("sort", sort);
//     model.addAttribute("order", order);
//     model.addAttribute("pageSize", size);
//     model.addAttribute("totalPages", carBookings.getTotalPages());
//     model.addAttribute("totalElements", carBookings.getTotalElements());
//     model.addAttribute("pageSizes", new int[] { 2, 5, 10, 20 });
//     return "SearchAndBook/search";  // Trả về trang kết quả tìm kiếm
// }

//     @GetMapping("detail")
//     public String detail(Model model) {
//         model.addAttribute("car", new Car());
//         return "SearchAndBook/detail";
//     }

//     @GetMapping("listCar")
//     public String listCar(Model model) {
//         model.addAttribute("car", new Car());
//         return "SearchAndBook/listCar";
//     }

//     @GetMapping("book")
//     public String book(Model model) {
//         model.addAttribute("car", new Car());
//         return "SearchAndBook/book";
//     }

// }
