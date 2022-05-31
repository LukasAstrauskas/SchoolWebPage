package com.webApp.school.controller;


import com.webApp.school.model.Task;
import com.webApp.school.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/teacher")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    @PostMapping("/save-task")
    public String saveTask(@ModelAttribute("task") Task task,
                           @RequestParam(required = false, value = "courseID") Long courseID) {
        taskService.save(task);
        return "redirect:/teacher/Courses/" + courseID;
    }

    @GetMapping("/delete-task/{taskID}/{courseID}")
    public String deleteTask(@PathVariable("taskID") Long taskID,
                             @PathVariable("courseID") Long courseID) {
        taskService.deleteById(taskID);
        return "redirect:/teacher/Courses/" + courseID;
    }
}
