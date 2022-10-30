package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.AccidentType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;

@Repository
public class TypeMem {

    private final HashMap<Integer, AccidentType> types = new HashMap<>(3);

    public TypeMem() {
        types.put(1, new AccidentType(1, "Two vehicles"));
        types.put(2, new AccidentType(2, "Vehicle and human"));
        types.put(3, new AccidentType(3, "Vehicle and bike"));
    }

    public Optional<AccidentType> findTypeById(int id) {
        return Optional.of(types.get(id));
    }

    public Collection<AccidentType> findAllTypes() {
        return new ArrayList<>(types.values());
    }
}
