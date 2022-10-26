package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.*;

@Repository
public class AccidentMem {

    private final HashMap<Integer, Accident> accidents = new HashMap<>();

    public Optional<Accident> add(Accident accident) {
        Accident acc = accidents.putIfAbsent(accident.getId(), accident);
        return Optional.ofNullable(acc);
    }

    public void update(Accident accident) {
        accidents.replace(accident.getId(), accident);
    }

    public void delete(Accident accident) {
        accidents.remove(accident.getId(), accident);
    }

    public Optional<Accident> findById(int id) {
        return Optional.of(accidents.get(id));
    }

    public Collection<Accident> findAll() {
        return new ArrayList<>(accidents.values());
    }
}
