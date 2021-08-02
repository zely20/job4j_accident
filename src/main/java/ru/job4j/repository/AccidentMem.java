package ru.job4j.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.model.Accident;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class AccidentMem {

    HashMap<Integer, Accident> accidents = new HashMap<Integer, Accident>();

    public List<Accident> findAll() {
        List list = new ArrayList(accidents.values());
        return list;
    }

    public Accident add(Accident accident) {
        return accidents.put(accident.getId(), accident);
    }

    public Accident remove(Integer id) {
        return accidents.remove(id);
    }

    public Accident findById(Integer id) {
        return accidents.get(id);
    }
}
