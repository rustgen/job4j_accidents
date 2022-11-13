package ru.job4j.accident.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.accident.model.Rule;

import java.util.Collection;
import java.util.Optional;

public interface RuleRepository extends CrudRepository<Rule, Integer> {

    @Query("FROM Rule")
    Collection<Rule> findAll();

    @Query("FROM Rule WHERE id = ?1")
    Optional<Rule> findById(Integer integer);
}
