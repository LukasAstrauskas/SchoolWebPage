package com.webApp.school.controller;

import com.webApp.school.model.EnrolCourse;
import com.webApp.school.model.Student;
import com.webApp.school.model.Task;
import com.webApp.school.model.Teacher;
import com.webApp.school.service.TeacherService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }


    @GetMapping("/admin/info-teacher")
    public String infoTeacher(@RequestParam("id") Long id, Model model) {
        Teacher teacher = teacherService.getById(id);
        model.addAttribute("user", teacher);
        return "user-info";
    }

    @GetMapping(value = {"/teacher/Courses", "/teacher/Courses/{courseID}"})
    public String courses(Model model, Authentication auth,
                          @PathVariable(required = false, value = "courseID") Long courseID) {
        if (courseID == null) {
            model.addAttribute("courses", teacherService.getCourses(auth));
            return "courses";
        } else {
            model.addAttribute("course", teacherService.getCourseByID(courseID, auth));
            model.addAttribute("task", new Task());
            return "course-info";
        }
    }


//    @GetMapping("/teacher/course-info/{id}")
//    public String houseInfo(@PathVariable("id") Long courseID, Model model, Authentication auth) {
//        model.addAttribute("course", teacherService.getCourseByID(courseID, auth));
//        model.addAttribute("studList", studentService.filterByCourse(courseID));
//        return "course-info";
//    }

}
