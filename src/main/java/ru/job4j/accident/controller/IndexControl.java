package ru.job4j.accident.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.service.AccidentService;
import ru.job4j.accident.service.RuleService;
import ru.job4j.accident.service.TypeService;

@Controller
@AllArgsConstructor
public class IndexControl {

    private final AccidentService accidentService;
    private final RuleService ruleService;
    private final TypeService typeService;

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        model.addAttribute("accidents", accidentService.findAll());
        model.addAttribute("types", typeService.findAllTypes());
        model.addAttribute("rules", ruleService.findAll());
        return "index";
    }
}