package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {

    private final HashMap<Integer, Accident> accidents = new HashMap<>();
    private final AtomicInteger ids = new AtomicInteger(accidents.size());

    public Optional<Accident> add(Accident accident) {
        accident.setId(ids.incrementAndGet());
        Accident acc = accidents.putIfAbsent(accident.getId(), accident);
        return Optional.ofNullable(acc);
    }

    public boolean update(Accident accident) {
        return accidents.replace(accident.getId(), accident) != null;
    }

    public boolean delete(Accident accident) {
        return accidents.remove(accident.getId(), accident);
    }

    public Optional<Accident> findById(int id) {
        return Optional.of(accidents.get(id));
    }

    public Collection<Accident> findAll() {
        return new ArrayList<>(accidents.values());
    }
}
