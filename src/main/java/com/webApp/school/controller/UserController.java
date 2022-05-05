package com.webApp.school.controller;

import com.webApp.school.model.User;
import com.webApp.school.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
//@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String getAllUsers(Model model) {
        model.addAttribute("userList", userService.getAll());
        return "users";
    }



    @GetMapping("/")
    public String indexPage() {
        return "index";
    }

    @GetMapping("/mainHall")
    public String homePage() {
        return "home";
    }

    @GetMapping("/home/{id}")
    public String singlePathVariable(@PathVariable("id") int id) {
        System.out.println(id);
        return "home";
    }

}
