package ru.job4j.accident.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentRepository;

import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccidentService {

    private final AccidentRepository accidentHibernate;

    public Optional<Accident> add(Accident accident) {
        return Optional.of(accidentHibernate.save(accident));
    }

    public void update(Accident accident) {
        accidentHibernate.save(accident);
    }

    public void delete(Accident accident) {
        accidentHibernate.delete(accident);
    }

    public Optional<Accident> findById(int id) {
        return accidentHibernate.findById(id);
    }

    public Collection<Accident> findAll() {
        return accidentHibernate.findAll();
    }
}
