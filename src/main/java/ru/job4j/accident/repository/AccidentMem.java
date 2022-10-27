package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {

    private final HashMap<Integer, Accident> accidents = new HashMap<>();
    private final AtomicInteger ids = new AtomicInteger(4);

    public AccidentMem() {
        accidents.put(1, new Accident(1, "accident 1",
                "Texting while driving", "09267 Anniversary Place"));
        accidents.put(2, new Accident(2, "accident 2",
                "Running a red light or disobeying a sign", "48649 Norway Maple Alley"));
        accidents.put(3, new Accident(3,
                "accident 3", "Driving under the influence", "57945 Shoshone Park"));
        accidents.put(4, new Accident(4,
                "accident 4", "Speeding", "88 Mesta Point"));
    }

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
