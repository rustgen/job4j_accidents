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
    private final AccidentRepository accidentHibernate;

    public Optional<Rule> findById(int id) {
        return ruleHibernate.findById(id);
    }

    public Collection<Rule> findAll() {
        return ruleHibernate.findAll();
    }

    public Set<Rule> getRulesById(int id) {
        Optional<Accident> optional = accidentHibernate.findById(id);
        return new HashSet<>(optional.orElseThrow(NoSuchElementException::new).getRules());
    }

    public void setRules(Accident accident) {
        accident.setRules(getRulesById(accident.getId()));
    }
}
