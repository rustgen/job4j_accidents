package ru.job4j.accident.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.accident.model.Rule;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

public interface RuleRepository extends CrudRepository<Rule, Integer> {

    @Query("FROM Rule")
    Collection<Rule> findAll();

    /* @Query("FROM Rule where id in (select rule_id from accident_rule where accident_id = ?1)")
    Set<Rule> findAllById(Iterable<Integer> integers); */

    @Query("FROM Rule WHERE id = ?1")
    Optional<Rule> findById(Integer integer);
}
