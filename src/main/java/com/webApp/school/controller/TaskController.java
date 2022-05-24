package com.webApp.school.controller;


import com.webApp.school.model.Task;
import com.webApp.school.service.CourseService;
import com.webApp.school.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/teacher")
public class TaskController {

    private final TaskService taskService;
    private final CourseService courseService;

    @Autowired
    public TaskController(TaskService taskService,
                          CourseService courseService) {
        this.taskService = taskService;
        this.courseService = courseService;
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
