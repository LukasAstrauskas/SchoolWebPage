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
    public String saveTask(@ModelAttribute("task") Task task) {
        taskService.save(task);
        return "redirect:/teacher/Courses";
    }

    @GetMapping("/delete-task/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteById(id);
        return "redirect:/teacher/Courses";
    }
}
