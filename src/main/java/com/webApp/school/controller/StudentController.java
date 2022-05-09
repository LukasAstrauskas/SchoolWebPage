package com.webApp.school.controller;

import com.webApp.school.model.User;
import com.webApp.school.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/admin/info-student")
    public String infoStudent(@RequestParam( "id") Long id, Model model) {
        model.addAttribute("user", studentService.getById(id));
        return "user-info";
    }

}
