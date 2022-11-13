package ru.job4j.accident.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.repository.AccidentTypeRepository;

import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TypeService {

    private final AccidentTypeRepository typeHibernate;

    public Optional<AccidentType> findTypeById(int id) {
        return typeHibernate.findById(id);
    }

    public Collection<AccidentType> findAllTypes() {
        return (Collection<AccidentType>) typeHibernate.findAll();
    }
}
