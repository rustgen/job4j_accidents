package ru.job4j.accident.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.service.AccidentService;

import java.util.Scanner;

@Controller
@AllArgsConstructor
public class IndexControl {

    private final AccidentService accidentService;

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("user", "new User");
        model.addAttribute("accidents", accidentService.findAll());
        return "index";
    }
}