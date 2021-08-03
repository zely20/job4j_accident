package ru.job4j.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.model.Accident;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {

    private final HashMap<Integer, Accident> accidents = new HashMap<Integer, Accident>();
    private static AtomicInteger id = new AtomicInteger(0);

    public List<Accident> findAll() {
        List list = new ArrayList(accidents.values());
        return list;
    }

    public Accident create(Accident accident) {
        if (accident.getId() == 0) {
            accident.setId(id.incrementAndGet());
        }
        return accidents.put(accident.getId(), accident);
    }

    public Accident remove(Integer id) {
        return accidents.remove(id);
    }

    public Accident findById(Integer id) {
        return accidents.get(id);
    }
}
