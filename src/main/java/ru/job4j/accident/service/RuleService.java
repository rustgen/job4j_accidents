package ru.job4j.accident.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.RuleRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

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

    public Set<Rule> getRulesById(int id) {
        return (Set<Rule>) ruleHibernate.findAllById(Collections.singleton(id));
    }

    public void setRules(Accident accident) {
        accident.setRules(getRulesById(accident.getId()));
    }
}
