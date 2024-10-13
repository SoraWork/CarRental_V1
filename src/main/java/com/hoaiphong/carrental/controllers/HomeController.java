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
}