package com.webApp.school.controller;

import com.webApp.school.model.Student;
import com.webApp.school.model.Teacher;
import com.webApp.school.service.TeacherService;
import org.springframework.security.core.Authentication;
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
        Teacher teacher = teacherService.getById(id);
        model.addAttribute("user",teacher );
        return "user-info";
    }

    @GetMapping("/teacher/Courses")
    public String courses(Model model, Authentication auth) {
        model.addAttribute("courses", teacherService.getCourses(auth));
        return "teacher/courses";
    }

}
