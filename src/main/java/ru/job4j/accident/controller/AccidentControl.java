package ru.job4j.accident.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.service.AccidentService;
import ru.job4j.accident.service.RuleService;
import ru.job4j.accident.service.TypeService;

import java.util.*;

@Controller
@AllArgsConstructor
public class AccidentControl {
    private final AccidentService accidentService;
    private final TypeService typeService;
    private final RuleService ruleService;

    @GetMapping("/createAccident")
    public String createAccident(Model model) {
        model.addAttribute("types", typeService.findAllTypes());
        model.addAttribute("rules", ruleService.findAll());
        return "createAccident";
    }

    @PostMapping("/saveAccident")
    public String save(@ModelAttribute Accident accident, @RequestParam("type.id") int id,
                       @RequestParam(value = "rIds", required = false) Set<String> rules) {
        accident.setType(typeService.findTypeById(id).orElseThrow(NoSuchElementException::new));
        setRulesByUpdate(accident, rules);
        accidentService.add(accident);
        return "redirect:/index";
    }

    @PostMapping("/updateAccident")
    public String update(@ModelAttribute Accident accident, @RequestParam("type.id") int id,
                         @RequestParam(value = "rIds", required = false) Set<String> rules) {
        accident.setType(typeService.findTypeById(id).orElseThrow(NoSuchElementException::new));
        setRulesByUpdate(accident, rules);
        accidentService.update(accident);
        return "redirect:/index";
    }

    @GetMapping("/formUpdateAccident")
    public String update(@RequestParam("id") int id, Model model) {
        model.addAttribute("types", typeService.findAllTypes());
        model.addAttribute("rules", ruleService.findAll());
        model.addAttribute("accident", accidentService.findById(id)
                .orElseThrow(NoSuchElementException::new));
        return "updateAccident";
    }

    private void setRulesByUpdate(Accident accident, Set<String> rules) {
        Set<Rule> set = new HashSet<>();
        for (String rule : rules) {
            int i = Integer.parseInt(rule);
            Optional<Rule> optional = ruleService.findById(i);
            optional.ifPresent(set::add);
        }
        accident.setRules(set);
    }
}
