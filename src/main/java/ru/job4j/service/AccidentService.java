package ru.job4j.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.entity.Accident;
import ru.job4j.entity.AccidentType;
import ru.job4j.entity.Rule;
import ru.job4j.repository.AccidentRepository;
import ru.job4j.repository.AccidentTypeRepository;
import ru.job4j.repository.RuleRepository;

import java.util.List;

@Service
public class AccidentService {

    private final AccidentRepository accidentRepository;
    private final RuleRepository ruleRepository;
    private final AccidentTypeRepository typeRepository;

    public AccidentService(AccidentRepository accidentRepository,
                           RuleRepository ruleRepository,
                           AccidentTypeRepository typeRepository) {
        this.accidentRepository = accidentRepository;
        this.ruleRepository = ruleRepository;
        this.typeRepository = typeRepository;
    }

    @Transactional
    public Accident saveAccident(Accident accident, String[] rIds) {
        for (String id : rIds) {
            accident.addRule(ruleRepository.findById(Integer.parseInt(id)).get());
        }
        return accidentRepository.save(accident);
    }

    @Transactional
    public void updateAccident(Accident accident, String[] rIds) {
        Accident current = getFetchAccidentById(accident.getId());
        current.setName(accident.getName());
        current.setType(accident.getType());
        current.getRules().clear();
        for (String id : rIds) {
            current.addRule(ruleRepository.findById(Integer.parseInt(id)).get());
        }
        accidentRepository.save(current);
    }

    @Transactional
    public void deleteAccident(int id) {
        accidentRepository.delete(getFetchAccidentById(id));
    }

    @Transactional
    public Accident getFetchAccidentById(int id) {
        return accidentRepository.getFetchAccidentById(id);
    }

    public List<Accident> getAllAccidents() {
        return (List<Accident>) accidentRepository.findAll();
    }
    public List<Accident> findAllFetchAccident() {
        return (List<Accident>) accidentRepository.fetchAccident();
    }

    public List<AccidentType> getAllTypes() {
        return (List<AccidentType>) typeRepository.findAll();
    }

    public List<Rule> getAllRules() {
        return (List<Rule>) ruleRepository.findAll();
    }
}
