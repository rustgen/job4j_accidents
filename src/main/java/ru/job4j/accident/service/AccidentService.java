package ru.job4j.accident.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentHibernate;

import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccidentService {

    private final AccidentHibernate accidentHibernate;

    public Optional<Accident> add(Accident accident) {
        return accidentHibernate.save(accident);
    }

    public void update(Accident accident) {
        accidentHibernate.update(accident);
    }

    public void delete(Accident accident) {
        accidentHibernate.delete(accident);
    }

    public Optional<Accident> findById(int id) {
        return accidentHibernate.findById(id);
    }

    public Collection<Accident> findAll() {
        return accidentHibernate.getAll();
    }
}
