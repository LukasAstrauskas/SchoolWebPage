package com.webApp.school.controller;

import com.webApp.school.model.User;
import com.webApp.school.service.EnrolTaskService;
import com.webApp.school.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class EnrolTaskController {

    private final EnrolTaskService enrolTaskService;
    private final UserService userService;

    public EnrolTaskController(EnrolTaskService enrolTaskService, UserService userService) {
        this.enrolTaskService = enrolTaskService;
        this.userService = userService;
    }



    @GetMapping("/updateTaskStatus")
    public String updateTaskStatus(@RequestParam(value = "taskID") Long enrTaskID,
                                   @RequestParam(value = "courseID") Long courseID ) {
        enrolTaskService.changeTaskStatus(enrTaskID);
        return "redirect:/student/Courses/"+courseID;
    }


}
