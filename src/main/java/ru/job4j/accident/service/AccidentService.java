package ru.job4j.accident.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentJdbcTemplate;

import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccidentService {

    private final AccidentJdbcTemplate accidentJdbcTemplate;

    public Optional<Accident> add(Accident accident) {
        return accidentJdbcTemplate.add(accident);
    }

    public boolean update(Accident accident) {
        return accidentJdbcTemplate.update(accident);
    }

    public boolean delete(Accident accident) {
        return accidentJdbcTemplate.delete(accident);
    }

    public Optional<Accident> findById(int id) {
        return accidentJdbcTemplate.findById(id);
    }

    public Collection<Accident> findAll() {
        return accidentJdbcTemplate.findAll();
    }
}
