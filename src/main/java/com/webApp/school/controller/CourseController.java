package com.webApp.school.controller;


import com.webApp.school.model.Course;
import com.webApp.school.service.CourseService;
import com.webApp.school.service.EnrolCourseService;
import com.webApp.school.service.StudentService;
import com.webApp.school.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class CourseController {

    private final CourseService courseService;
    private final StudentService studentService;
    private final EnrolCourseService enrolCourseService;
    private final TeacherService teacherService;

    @Autowired
    public CourseController(CourseService courseService,
                            StudentService studentService,
                            EnrolCourseService enrolCourseService, TeacherService teacherService) {
        this.courseService = courseService;
        this.studentService = studentService;
        this.enrolCourseService = enrolCourseService;
        this.teacherService = teacherService;
    }

    @GetMapping(value = {"/Courses", "/Courses/{id}"})
    public String courses(@PathVariable(required = false) Long id, Model model) {
        if (id == null) {
            model.addAttribute("course", new Course());
        } else {
            model.addAttribute("course", courseService.getById(id));
        }
        model.addAttribute("courses", courseService.getAll());
        model.addAttribute("teachers", teacherService.getAll());
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
    public String houseInfo(@PathVariable("id") Long courseID, Model model) {
        model.addAttribute("course", courseService.getById(courseID));
//        TODO filter studs enrolled in course in course
        Course course = courseService.getById(courseID);
        model.addAttribute("studList", studentService.filterByCourse(courseID));
        return "course-info";
    }

    @GetMapping("/studentToCourse")
    public String studentToCourse(@RequestParam("courseID") Long courseID,
                             @RequestParam("studentID") Long studentID) {
        enrolCourseService.addStudentToCourse(studentID, courseID);
        return "redirect:/admin/course-info/" + courseID;
    }

    @GetMapping("/removeFromCourse")
    public String removeFromCourse(@RequestParam("courseID") Long courseID,
                                  @RequestParam("enrID") Long enrID) {
//        studentService.removeFromHouse(studentID);
        enrolCourseService.deleteById(enrID);
        return "redirect:/admin/course-info/" + courseID;
    }

}
