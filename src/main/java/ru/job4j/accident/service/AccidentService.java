package ru.job4j.accident.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentMem;

import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccidentService {

    private final AccidentMem accidentMem;

    public Optional<Accident> add(Accident accident) {
        return accidentMem.add(accident);
    }

    public boolean update(Accident accident) {
        return accidentMem.update(accident);
    }

    public boolean delete(Accident accident) {
        return accidentMem.delete(accident);
    }

    public Optional<Accident> findById(int id) {
        return accidentMem.findById(id);
    }

    public Collection<Accident> findAll() {
        return accidentMem.findAll();
    }
}
