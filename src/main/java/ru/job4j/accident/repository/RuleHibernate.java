package ru.job4j.accident.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.*;

@Repository
@AllArgsConstructor
public class RuleHibernate {

    private final CrudRepository crudRepository;

    public Optional<Rule> findById(int id) {
        return crudRepository.optional("FROM rule WHERE id = :fId",
                Rule.class,
                Map.of("fId", id));
    }

    public Collection<Rule> findAll() {
        return crudRepository.query("FROM rule",
                Rule.class);
    }

    public Collection<Rule> getRulesById(int id) {
        return crudRepository.query("select * from rule where id in "
                                    + "(select rule_id from accident_rule where accident_id = fId)",
                Rule.class,
                Map.of("fId", id));
    }
}
