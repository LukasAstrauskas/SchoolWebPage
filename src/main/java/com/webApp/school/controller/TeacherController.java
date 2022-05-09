package com.webApp.school.controller;

import com.webApp.school.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }


    @GetMapping("/admin/info-teacher")
    public String infoTeacher(@RequestParam( "id") Long id, Model model) {
        model.addAttribute("user", teacherService.getById(id));
        return "user-info";
    }

}
