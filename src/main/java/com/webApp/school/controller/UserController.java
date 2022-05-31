package com.webApp.school.controller;

import com.webApp.school.model.User;
import com.webApp.school.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/admin")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping(value = {"/Users", "/Users/{id}"})
    public String users(@PathVariable(required = false) Long id, Model model) {
        if (id == null) {
            model.addAttribute("user", new User());
        } else {
            model.addAttribute("user", userService.getById(id));
//            return "forward:/admin/info-user";
        }
        model.addAttribute("users", userService.getAll());
        return "users";
    }

    @GetMapping("/info-user")
    public String infoUser(@RequestParam("id") Long id, Model model) {
        User user = userService.getById(id);
        model.addAttribute("user", user);
        return "user-info";
    }


    @PostMapping("/save-user")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/admin/Users";
    }

    @GetMapping("/delete-user/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return "redirect:/admin/Users";
    }




}
