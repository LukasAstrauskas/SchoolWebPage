package com.webApp.school.controller;


import com.webApp.school.model.Task;
import com.webApp.school.service.CourseService;
import com.webApp.school.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TaskController {

    private final TaskService taskService;
    private final CourseService courseService;

    @Autowired
    public TaskController(TaskService taskService,
                          CourseService courseService) {
        this.taskService = taskService;
        this.courseService = courseService;
    }

    @GetMapping(value = {"/Tasks", "/Tasks/{id}"})
    public String tasks(@PathVariable(required = false) Long id, Model model) {
        if (id == null) {
            model.addAttribute("task", new Task());
        } else {
            model.addAttribute("task", taskService.getById(id));
        }
        model.addAttribute("tasks", taskService.getAll());
        model.addAttribute("courses", courseService.getAll());
        return "tasks";
    }

    @PostMapping("/save-task")
    public String saveTask(@ModelAttribute("task") Task task) {
        taskService.save(task);
        return "redirect:/teacher/Courses";
    }

    @GetMapping("/delete-task/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteById(id);
        return "redirect:/Tasks";
    }
}
