package ru.job4j.accident.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.service.AccidentService;
import ru.job4j.accident.service.RuleService;

import java.util.Scanner;

@Controller
@AllArgsConstructor
public class IndexControl {

    private final AccidentService accidentService;
    private final RuleService ruleService;

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("user", "new User");
        model.addAttribute("accidents", accidentService.findAll());
        model.addAttribute("types", accidentService.findAllTypes());
        model.addAttribute("rules", ruleService.findAll());
        return "index";
    }
}