package com.webApp.school.controller;

import com.webApp.school.service.EnrolTaskService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EnrolTaskController {

    private final EnrolTaskService enrolTaskService;

    public EnrolTaskController(EnrolTaskService enrolTaskService) {
        this.enrolTaskService = enrolTaskService;
    }

    @GetMapping("/updateTaskStatus")
    public String updateTaskStatus(@RequestParam(value = "taskID") Long enrTaskId,
                                   @RequestParam(value = "userID", required = false) Long userId) {
        enrolTaskService.changeTaskStatus(enrTaskId);
        return "redirect:/admin/info-student?id=" + userId;
    }


}
