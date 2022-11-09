package ru.job4j.accident.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.AccidentType;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class TypeHibernate {

    private final CrudRepository crudRepository;

    public Optional<AccidentType> findTypeById(int id) {
        return crudRepository.optional("FROM type WHERE id = :fId",
                AccidentType.class,
                Map.of("fId", id));
    }

    public Collection<AccidentType> findAllTypes() {
        return crudRepository.query("FROM type",
                AccidentType.class);
    }
}
