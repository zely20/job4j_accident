package ru.job4j.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.model.AccidentType;

public interface AccidentTypeRepository extends CrudRepository<AccidentType, Integer> {
}
