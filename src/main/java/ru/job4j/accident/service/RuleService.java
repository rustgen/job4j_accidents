package ru.job4j.accident.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.RuleHibernate;
import ru.job4j.accident.repository.RuleJdbcTemplate;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class RuleService {

    private final RuleHibernate ruleHibernate;

    public Optional<Rule> findById(int id) {
        return ruleHibernate.findById(id);
    }

    public Collection<Rule> findAll() {
        return ruleHibernate.findAll();
    }

    public Set<Rule> getRulesById(int id) {
        return new HashSet<>(ruleHibernate.getRulesById(id));
    }

    public void setRules(Accident accident) {
        accident.setRules(getRulesById(accident.getId()));
    }
}
