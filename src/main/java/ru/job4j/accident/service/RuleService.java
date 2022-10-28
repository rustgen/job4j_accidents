package ru.job4j.accident.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.RuleMem;

import java.util.*;

@Service
@AllArgsConstructor
public class RuleService {

    private final RuleMem ruleMem;

    public Optional<Rule> add(Rule rule) {
        return ruleMem.add(rule);
    }

    public boolean update(Rule rule) {
        return ruleMem.update(rule);
    }

    public Optional<Rule> findById(int id) {
        return ruleMem.findById(id);
    }

    public Collection<Rule> findAll() {
        return ruleMem.findAll();
    }

    public List<String> findAllNames() {
        return ruleMem.findAllNames();
    }

    public Set<Rule> getByIds(Set<Integer> ids) {
        return ruleMem.getByIds(ids);
    }

    public void setRules(Accident accident) {
        Set<Integer> set = new HashSet<>();
        for (Rule rule : accident.getRules()) {
            set.add(rule.getId());
        }
        accident.setRules(getByIds(set));
    }
}
