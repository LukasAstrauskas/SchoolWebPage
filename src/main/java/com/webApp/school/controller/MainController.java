package com.webApp.school.controller;


import com.webApp.school.model.SchoolUserDetails;
import com.webApp.school.model.User;
import com.webApp.school.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class MainController {

    private final UserService userService;

    public MainController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/")
    public String indexPage() {
        return "index";
    }

    @GetMapping("/mainHall")
    public String homePage() {
        return "home";
    }

    @GetMapping("/registered")
    public String register(Principal principal, Model model, SchoolUserDetails userDetails) {
        String email = principal.getName();
        User user = userService.getUserByEmail(email);
        model.addAttribute("user", user);
        return "register";
    }

}
