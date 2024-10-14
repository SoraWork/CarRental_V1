package com.hoaiphong.carrental.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hoaiphong.carrental.dtos.user.UserDTOBase;
import com.hoaiphong.carrental.entities.User;
import com.hoaiphong.carrental.repositories.UserRepository;
import com.hoaiphong.carrental.services.AuthService;
import com.hoaiphong.carrental.services.RoleService;
import com.hoaiphong.carrental.services.UserService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/")
public class AuthController {

    private final AuthService authService;
    private final RoleService roleService;
    private final UserService userService;
    private final UserRepository userRepository;


    public AuthController(AuthService authService, RoleService roleService,  UserRepository userRepository, UserService userService) {
        this.userService = userService;
        this.roleService = roleService;
        this.authService = authService;
        this.userRepository = userRepository;
    }

    @GetMapping("/auth/login")
    public String login(Model model, @RequestParam(required = false) String errorMessage) {
        // Nếu có thông báo lỗi từ yêu cầu, thêm nó vào mô hình
        if (errorMessage != null) {
            model.addAttribute("errorMessage", errorMessage);
        }

        model.addAttribute("userDTOBase", new UserDTOBase());
        return "auth/login";
    }

    @GetMapping("/auth/resetpassword")
    public String signup() {
        return "auth/resetpassword";
    }
    @GetMapping("/auth/resetpassword")
    public String forgotPassordProcess(@ModelAttribute UserDTOBase userDTOBase) {
        String output = "";
        User user = userRepository.findByEmail(userDTOBase.getEmail());
        if (user != null) {
			output = userService.sendEmail(user);
		}
		if (output.equals("success")) {
			return "redirect:/forgotPassword?success";
		}

        return "auth/resetpassword";
    }

    @PostMapping("/auth/register")
    public String register(@ModelAttribute("userDTOBase") @Valid UserDTOBase userDTOBase, BindingResult result) {

        if (result.hasErrors()) {
            return "auth/login";
        }

        authService.save(userDTOBase);
        return "redirect:/auth/login";
    }

}