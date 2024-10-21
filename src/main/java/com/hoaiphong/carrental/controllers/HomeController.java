package com.hoaiphong.carrental.controllers;

import org.hibernate.validator.constraints.UUID;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.hoaiphong.carrental.dtos.user.UserUpdateDTO;
import com.hoaiphong.carrental.dtos.user.UserUpdatePasswordDTO;
import com.hoaiphong.carrental.services.UserService;

import jakarta.validation.Valid;

@Controller
public class HomeController {

    private final UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String index( ) {
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

    @GetMapping("myprofile")
    public String myProfile(Model model) {
        model.addAttribute("userUpdateDTO", new UserUpdateDTO());
        return "home/myprofile";
    }

    @PostMapping("myprofile")
    public String myProfile(@ModelAttribute @Valid UserUpdateDTO userUpdateDTO, BindingResult bindingResult,
            Model model,
            @AuthenticationPrincipal UserDetails userDetails) {
        // if (bindingResult.hasErrors()) {
        // return "home/userprofile";
        // }
        var updatemember = userService.update(userUpdateDTO, userDetails.getUsername());
        if (updatemember == null) {
            model.addAttribute("message", "create content failed");
            return "home/userprofile";
        }
        return "redirect:/";
    }

    @PostMapping("myprofile-updatepassword")
    public String updatePassword(@ModelAttribute @Valid UserUpdatePasswordDTO userUpdatePasswordDTO, BindingResult bindingResult,
            Model model,
            @AuthenticationPrincipal UserDetails userDetails) {

        if (bindingResult.hasErrors()) {
        return "home/userprofile";
        }
        var updatemember = userService.update(userUpdatePasswordDTO, userDetails.getUsername());

        if (updatemember == null) {
            model.addAttribute("message", "create content failed");
            return "home/userprofile";
        }
        return "redirect:/";
    }

    @GetMapping("mywallet")
    public String myWallet() {
        return "home/mywallet";
    }
}