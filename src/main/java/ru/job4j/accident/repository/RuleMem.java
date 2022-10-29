package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Rule;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class RuleMem {
    private final HashMap<Integer, Rule> rules = new HashMap<>();
    private final AtomicInteger ids = new AtomicInteger(3);

    public RuleMem() {
        rules.put(1, new Rule(1, "Article. 1"));
        rules.put(2, new Rule(2, "Article. 2"));
        rules.put(3, new Rule(3, "Article. 3"));
    }

    public Optional<Rule> add(Rule rule) {
        rule.setId(ids.incrementAndGet());
        Rule addRule = rules.putIfAbsent(rule.getId(), rule);
        return Optional.ofNullable(addRule);
    }

    public boolean update(Rule rule) {
        return rules.replace(rule.getId(), rule) != null;
    }

    public Optional<Rule> findById(int id) {
        return Optional.of(rules.get(id));
    }

    public Collection<Rule> findAll() {
        return new ArrayList<>(rules.values());
    }

    public Set<Rule> getByIds(Set<Integer> ids) {
        return ids.stream().map(rules::get).collect(Collectors.toSet());
    }
}
