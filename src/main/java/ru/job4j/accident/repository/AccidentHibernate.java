package ru.job4j.accident.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class AccidentHibernate {

    private final CrudRepository crudRepository;

    public Optional<Accident> save(Accident accident) {
        crudRepository.run(session -> session.persist(accident));
        return Optional.of(accident);
    }

    public List<Accident> getAll() {
        return crudRepository.query("FROM accident join fetch type join fetch rule",
                Accident.class);
    }

    public void update(Accident accident) {
        crudRepository.run(session -> session.merge(accident));
    }

    public void delete(Accident accident) {
        crudRepository.run(
                "delete from accident where id = :fId",
                Map.of("fId", accident.getId())
        );
    }

    public Optional<Accident> findById(int id) {
        return crudRepository.optional("FROM accident WHERE id = :fId",
                Accident.class,
                Map.of("fId", id));
    }
}
