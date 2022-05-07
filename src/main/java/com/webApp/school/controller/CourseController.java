package com.webApp.school.controller;


import com.webApp.school.model.Course;
import com.webApp.school.service.CourseService;
import com.webApp.school.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class CourseController {

    private final CourseService courseService;
    private final StudentService studentService;

    @Autowired
    public CourseController(CourseService courseService,
                            StudentService studentService) {
        this.courseService = courseService;
        this.studentService = studentService;
    }

    @GetMapping(value = {"/Courses", "/Courses/{id}"})
    public String courses(@PathVariable(required = false) Long id, Model model) {
        if (id == null) {
            model.addAttribute("course", new Course());
        } else {
            model.addAttribute("course", courseService.getById(id));
        }
        model.addAttribute("courses", courseService.getAll());
        return "courses";
    }

    @PostMapping("/save-course")
    public String saveCourse(@ModelAttribute("course") Course course) {
        courseService.save(course);
        return "redirect:/admin/Courses";
    }

    @GetMapping("/delete-course/{id}")
    public String deleteCourse(@PathVariable Long id) {
        courseService.deleteById(id);
        return "redirect:/admin/Courses";
    }

    @GetMapping("/course-info/{id}")
    public String houseInfo(@PathVariable("id") Long id, Model model) {
        Course course = courseService.getById(id);
        model.addAttribute("course", courseService.getById(id));
//        TODO filter studs enrolled in course in course
        model.addAttribute("studList", studentService.getAll());
        return "course-info";
    }

    @GetMapping("/studentToCourse")
    public String addStudent(@RequestParam("houseID") Long courseID,
                             @RequestParam("studentID") Long studentID) {
        studentService.studentEnrolCourse(studentID, courseID);
        return "redirect:/admin/course-info/" + courseID;
    }

//    @GetMapping("/removeFromCourse")
//    public String removeFromHouse(@RequestParam("houseID") Long houseID,
//                                  @RequestParam("studentID") Long studentID) {
//        studentService.removeFromHouse(studentID);
//        return "redirect:/admin/info-house" + "?id=" + houseID;
//    }

}
