package ru.job4j.repository;

import ru.job4j.entity.Accident;
import ru.job4j.entity.AccidentType;
import ru.job4j.entity.Rule;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

//@Repository
public class AccidentMem {

    private final Map<Integer, Accident> accidents = new HashMap<Integer, Accident>();
    private final Map<Integer, AccidentType> types = new HashMap<>();
    private final Map<Integer, Rule> rules = new HashMap<>();
    private static AtomicInteger id = new AtomicInteger(0);

    public AccidentMem(){
        types.put(0, AccidentType.of(0, "не выбрано"));
        types.put(1, AccidentType.of(1, "Превышение скорости"));
        types.put(2, AccidentType.of(2, "Обгон в неположенном месте"));
        types.put(3, AccidentType.of(3, "Стоянка в запрещенном месте"));
        types.put(4, AccidentType.of(4, "Нарушение разметки"));

        rules.put(0, Rule.of(0, "Статья. 1"));
        rules.put(1, Rule.of(1, "Статья. 2"));
        rules.put(2, Rule.of(2, "Статья. 3"));
    }

    public List<Accident> findAll() {
        List list = new ArrayList(accidents.values());
        return list;
    }

    public List<AccidentType> getAllTypes() {
        List list = new ArrayList(types.values());
        return list;
    }

    public List<Rule> getAllRules() {
        List list = new ArrayList(rules.values());
        return list;
    }

    public Accident create(Accident accident, String [] ids) {
        if (accident.getId() == 0) {
            accident.setId(id.incrementAndGet());
        }
        Set<Rule> rulesForCurrentAccident = new HashSet<>();
        for (String ruleId : ids) {
            rulesForCurrentAccident.add(getRuleById(Integer.parseInt(ruleId)));
        }
        accident.setRules(rulesForCurrentAccident);
        accident.setType(types.get(accident.getType().getId()));
        return accidents.put(accident.getId(), accident);
    }

    public Accident remove(Integer id) {
        return accidents.remove(id);
    }

    public Accident findById(Integer id) {
        return accidents.get(id);
    }

    public Rule getRuleById(int id) {
        return rules.get(id);
    }
}
