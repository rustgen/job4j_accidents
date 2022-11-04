package ru.job4j.accident.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.RuleJdbcTemplate;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class RuleService {

    private final RuleJdbcTemplate ruleJdbcTemplate;

    public void add(Collection<Rule> rules, int accidentID) {
        ruleJdbcTemplate.add(rules, accidentID);
    }

    public void update(Collection<Rule> rules, int accidentID) {
        ruleJdbcTemplate.update(rules, accidentID);
    }

    public Optional<Rule> findById(int id) {
        return ruleJdbcTemplate.findById(id);
    }

    public Collection<Rule> findAll() {
        return ruleJdbcTemplate.findAll();
    }

    public Set<Rule> getRulesById(int id) {
        return new HashSet<>(ruleJdbcTemplate.getRulesById(id));
    }

    public void setRules(Accident accident) {
        accident.setRules(getRulesById(accident.getId()));
    }
}
