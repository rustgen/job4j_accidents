package ru.job4j.accident.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.service.AccidentService;

import java.util.NoSuchElementException;

@Controller
@AllArgsConstructor
public class AccidentControl {
    private final AccidentService accidentService;

    @GetMapping("/createAccident")
    public String createAccident(Model model) {
        model.addAttribute("types", accidentService.findAllTypes());
        return "createAccident";
    }

    @PostMapping("/saveAccident")
    public String save(@ModelAttribute Accident accident, @RequestParam("type.id") int id) {
        accident.setType(accidentService.findTypeById(id).orElseThrow(NoSuchElementException::new));
        accidentService.add(accident);
        return "redirect:/index";
    }

    @PostMapping("/updateAccident")
    public String update(@ModelAttribute Accident accident, @RequestParam("type.id") int id) {
        accident.setType(accidentService.findTypeById(id).orElseThrow(NoSuchElementException::new));
        accidentService.update(accident);
        return "redirect:/index";
    }

    @GetMapping("/formUpdateAccident")
    public String update(@RequestParam("id") int id, Model model) {
        model.addAttribute("types", accidentService.findAllTypes());
        model.addAttribute("accident", accidentService.findById(id)
                .orElseThrow(NoSuchElementException::new));
        return "updateAccident";
    }
}
