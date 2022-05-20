package com.webApp.school.controller;


import com.webApp.school.model.SchoolUserDetails;
import com.webApp.school.model.Student;
import com.webApp.school.model.User;
import com.webApp.school.service.UserService;
import org.springframework.security.core.Authentication;
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

    @GetMapping("/profile")
    public String register(Principal principal, Model model) {
        String email = principal.getName();
        User user = userService.getUserByEmail(email);
        model.addAttribute("user", user);
        return "profile";
    }

    @GetMapping("/courses")
    public String courses(Principal principal, Model model) {
        User user = userService.getUserByEmail(principal.getName());
        model.addAttribute("user", user);
        return "courses";
    }

}
