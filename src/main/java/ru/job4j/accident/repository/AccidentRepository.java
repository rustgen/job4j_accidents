package ru.job4j.accident.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.accident.model.Accident;

import java.util.Collection;

public interface AccidentRepository extends CrudRepository<Accident, Integer> {

    @Query("FROM Accident a join fetch a.type join fetch a.rules")
    Collection<Accident> findAll();
}
