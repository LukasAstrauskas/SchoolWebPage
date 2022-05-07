package com.webApp.school.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {


    @GetMapping("/")
    public String indexPage() {
        return "index";
    }

    @GetMapping("/mainHall")
    public String homePage() {
        return "home";
    }


}
