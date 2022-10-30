package ru.job4j.accident.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.repository.TypeJdbcTemplate;

import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TypeService {

    private final TypeJdbcTemplate typeJdbcTemplate;

    public Optional<AccidentType> findTypeById(int id) {
        return typeJdbcTemplate.findTypeById(id);
    }

    public Collection<AccidentType> findAllTypes() {
        return typeJdbcTemplate.findAllTypes();
    }
}
