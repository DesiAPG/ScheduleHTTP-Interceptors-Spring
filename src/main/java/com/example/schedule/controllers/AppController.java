package com.example.schedule.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {
    @GetMapping({"/", "/index"})
    public String index(Model model) {
        model.addAttribute("title", "Welcome to the attention schedule to customers");
        return "index";
    }

    @GetMapping("/closed")
    public String closed(Model model) {
        model.addAttribute("title", "We are closed");
        return "closed";
    }
}
