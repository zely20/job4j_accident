package ru.job4j.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.model.Accident;
import ru.job4j.model.AccidentType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {

    private final Map<Integer, Accident> accidents = new HashMap<Integer, Accident>();
    private final Map<Integer, AccidentType> types = new HashMap<>();
    private static AtomicInteger id = new AtomicInteger(0);

    public AccidentMem(){
        types.put(0, AccidentType.of(0, "не выбрано"));
        types.put(1, AccidentType.of(1, "Превышение скорости"));
        types.put(2, AccidentType.of(2, "Обгон в неположенном месте"));
        types.put(3, AccidentType.of(3, "Стоянка в запрещенном месте"));
        types.put(4, AccidentType.of(4, "Нарушение разметки"));
    }

    public List<Accident> findAll() {
        List list = new ArrayList(accidents.values());
        return list;
    }

    public List<AccidentType> getAllTypes() {
        List list = new ArrayList(types.values());
        return list;
    }

    public Accident create(Accident accident) {
        if (accident.getId() == 0) {
            accident.setId(id.incrementAndGet());
        }
        accident.setType(types.get(accident.getType().getId()));
        return accidents.put(accident.getId(), accident);
    }

    public Accident remove(Integer id) {
        return accidents.remove(id);
    }

    public Accident findById(Integer id) {
        return accidents.get(id);
    }
}
