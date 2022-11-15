package ru.job4j.accident.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentRepository;
import ru.job4j.accident.repository.RuleRepository;

import java.util.*;

@Service
@AllArgsConstructor
public class RuleService {

    private final RuleRepository ruleHibernate;

    public Optional<Rule> findById(int id) {
        return ruleHibernate.findById(id);
    }

    public Collection<Rule> findAll() {
        return ruleHibernate.findAll();
    }
}
