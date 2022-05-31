package com.webApp.school.controller;

import com.webApp.school.model.Task;
import com.webApp.school.model.Teacher;
import com.webApp.school.service.TeacherService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping(value = {"/teacher/Courses", "/teacher/Courses/{courseID}", "/teacher/Courses/{courseID}/{taskID}"})
    public String courses(Model model, Authentication auth,
                          @PathVariable(required = false, value = "courseID") Long courseID,
                          @PathVariable(required = false, value = "taskID") Long taskID) {
        if (courseID == null) {
            model.addAttribute("courses", teacherService.getCourses(auth));
            return "courses";
        } else {
            model.addAttribute("course", teacherService.getCourseByID(courseID, auth));
            if (taskID == null) {
                model.addAttribute("task", new Task());
            } else {
                model.addAttribute("task", teacherService.getTeacherTaskByID(auth, taskID));
            }
            return "course-info";
        }
    }
}
