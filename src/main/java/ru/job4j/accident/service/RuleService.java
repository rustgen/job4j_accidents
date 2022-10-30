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

    public Optional<Rule> add(Rule rule) {
        return ruleJdbcTemplate.add(rule);
    }

    public boolean update(Rule rule) {
        return ruleJdbcTemplate.update(rule);
    }

    public Optional<Rule> findById(int id) {
        return ruleJdbcTemplate.findById(id);
    }

    public Collection<Rule> findAll() {
        return ruleJdbcTemplate.findAll();
    }

    public Set<Rule> getByIds(Set<Integer> ids) {
        return ruleJdbcTemplate.getByIds(ids);
    }

    public void setRules(Accident accident) {
        Set<Integer> set = new HashSet<>();
        for (Rule rule : accident.getRules()) {
            set.add(rule.getId());
        }
        accident.setRules(getByIds(set));
    }
}
