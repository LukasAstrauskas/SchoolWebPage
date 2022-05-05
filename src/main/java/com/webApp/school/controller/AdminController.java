package com.webApp.school.controller;

import com.webApp.school.model.Course;
import com.webApp.school.model.User;
import com.webApp.school.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

//    @GetMapping("/addUser")
//    public String addPage(Model model) {
//        return "ad";
//    }

    @GetMapping(value = {"/Users", "/Users/{id}"})
    public String addUserForms(@PathVariable(required = false) Long id, Model model) {
        if (id == null){
            model.addAttribute("user", new User());
        } else {
            model.addAttribute("user", userService.getById(id));
        }
        model.addAttribute("users", userService.getAll());
        model.addAttribute("course", new Course());
        return "users";
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

    @PostMapping("/save-course")
    public String saveCourse(@ModelAttribute("course") Course course) {
        System.out.println(course.getName());
        return "redirect:/admin/Users";
    }
}
