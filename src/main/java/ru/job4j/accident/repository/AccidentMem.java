package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {

    private final HashMap<Integer, Accident> accidents = new HashMap<>();
    private final HashMap<Integer, AccidentType> accidentTypes = new HashMap<>(3);
    private final AtomicInteger idsAccident = new AtomicInteger(4);

    public AccidentMem() {
        accidentTypes.put(1, new AccidentType(1, "Two vehicles"));
        accidentTypes.put(2, new AccidentType(2, "Vehicle and human"));
        accidentTypes.put(3, new AccidentType(3, "Vehicle and bike"));
        accidents.put(1, new Accident(1, "accident 1", "Texting while driving",
                "09267 Anniversary Place", accidentTypes.get(1)));
        accidents.put(2, new Accident(2, "accident 2", "Running a red light or disobeying a sign",
                "48649 Norway Maple Alley", accidentTypes.get(2)));
        accidents.put(3, new Accident(3, "accident 3", "Driving under the influence",
                "57945 Shoshone Park", accidentTypes.get(1)));
        accidents.put(4, new Accident(4, "accident 4", "Speeding",
                "88 Mesta Point", accidentTypes.get(3)));
    }

    public Optional<Accident> add(Accident accident) {
        accident.setId(idsAccident.incrementAndGet());
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

    public Optional<AccidentType> findTypeById(int id) {
        return Optional.of(accidentTypes.get(id));
    }

    public Collection<AccidentType> findAllTypes() {
        return new ArrayList<>(accidentTypes.values());
    }
}
