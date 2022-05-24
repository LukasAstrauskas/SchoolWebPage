package com.webApp.school.controller;

import com.webApp.school.model.EnrolCourse;
import com.webApp.school.service.StudentService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/admin/info-student")
    public String infoStudent(@RequestParam("id") Long id, Model model) {
        model.addAttribute("user", studentService.getById(id));
        return "user-info";
    }

    @GetMapping({"/student/Courses", "/student/Courses/{courseID}"})
    public String courses(Model model, Authentication auth,
                          @PathVariable(required = false, value = "courseID") Long courseID) {
        if (courseID == null) {
            model.addAttribute("courses", studentService.getStudentCourses(auth));
            return "courses";
        } else {
            EnrolCourse enrCourse = studentService.getStudentEnrolledCourse(auth, courseID);
            model.addAttribute("enrC", enrCourse);
            return "course-info";
        }
    }



}
