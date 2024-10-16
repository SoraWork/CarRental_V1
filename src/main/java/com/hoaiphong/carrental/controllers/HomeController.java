package com.hoaiphong.carrental.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping
    public String index() {
        return "home/index";
    }

    @GetMapping("customer")
    public String customer() {
        return "customer/customer";
    }

    @GetMapping("owner")
    public String owner() {
        return "owner/owner";
    }

    @GetMapping("addcar1") 
        public String addCar1()  {
            return "owner/addCar/addCar1";
        }

        @GetMapping("addCar2") 
        public String addCar2()  {
            return "owner/addCar/addCar2";
        }
        @GetMapping("addCar3")
        public String addCar3()  {
            return "owner/addCar/addCar3";
        }
        @GetMapping("addCar4")
        public String addCar4()  {
            return "owner/addCar/addCar4";
        }

}
