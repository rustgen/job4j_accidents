package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;

import java.util.HashMap;

@Repository
@AllArgsConstructor
public class AccidentMem {

    private final HashMap<Integer, Accident> accidents;
}
