package com.webApp.school.controller;

import com.webApp.school.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/info-Admin")
    public String infoAdmin(@RequestParam("id") Long id, Model model) {
        model.addAttribute("user", adminService.getById(id));
        return "user-info";
    }

}
