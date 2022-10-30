package ru.job4j.accident.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.repository.TypeMem;

import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TypeService {

    private final TypeMem typeMem;

    public Optional<AccidentType> findTypeById(int id) {
        return typeMem.findTypeById(id);
    }

    public Collection<AccidentType> findAllTypes() {
        return typeMem.findAllTypes();
    }
}
