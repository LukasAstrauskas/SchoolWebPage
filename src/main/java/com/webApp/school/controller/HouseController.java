package com.webApp.school.controller;


import com.webApp.school.model.House;
import com.webApp.school.service.HouseService;
import com.webApp.school.service.StudentService;
import com.webApp.school.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class HouseController {

    private final HouseService houseService;
    private final TeacherService teacherService;
    private final StudentService studentService;

    @Autowired
    public HouseController(HouseService houseService,
                           TeacherService teacherService,
                           StudentService studentService) {
        this.houseService = houseService;
        this.teacherService = teacherService;
        this.studentService = studentService;
    }

    @GetMapping(value = {"/Houses", "/Houses/{id}"})
    public String houses(@PathVariable(required = false, value = "id") Long houseID, Model model) {
        if (houseID == null) {
            model.addAttribute("house", new House());
            model.addAttribute("teachers", teacherService.getAllTeachersNoHouse());
        } else {
            model.addAttribute("house", houseService.getById(houseID));
            model.addAttribute("teachers", teacherService
                    .getAllTeachersNoHouse(houseService.getById(houseID)));
        }
        model.addAttribute("houses", houseService.getAll());
        return "houses";
    }

    @PostMapping("/save-house")
    public String saveHouse(@ModelAttribute("house") House house) {
        houseService.save(house);
        return "redirect:/admin/Houses";
    }

    @GetMapping("/delete-house/{id}")
    public String deleteHouse(@PathVariable Long id) {
        houseService.deleteById(id);
        return "redirect:/admin/Houses";
    }

    @GetMapping("/info-house")
    public String infoHouse(@RequestParam("id") Long id, Model model) {
        model.addAttribute("house", houseService.getById(id));
        model.addAttribute("studList", studentService.getStudentsWithNoHouse());
        return "house-info";
    }

    @GetMapping("/studentToHouse")
    public String addStudent(@RequestParam("houseID") Long houseID,
                             @RequestParam("studentID") Long studentID) {
        studentService.setHouseToStudent(studentID, houseID);
        return "redirect:/admin/info-house" + "?id=" + houseID;
    }

    @GetMapping("/removeFromHouse")
    public String removeFromHouse(@RequestParam("houseID") Long houseID,
                                  @RequestParam("studentID") Long studentID) {
        studentService.removeFromHouse(studentID);
        return "redirect:/admin/info-house" + "?id=" + houseID;
    }

}
