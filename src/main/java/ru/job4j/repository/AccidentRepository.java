package ru.job4j.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.job4j.model.Accident;

import java.util.List;

public interface AccidentRepository extends CrudRepository<Accident, Integer> {

    @Query("SELECT DISTINCT accident FROM Accident accident " +
            "JOIN FETCH accident.rules")
    List<Accident> fetchAccident();

    @Query("SELECT DISTINCT accident FROM Accident accident " +
            "JOIN FETCH accident.rules " +
            "WHERE accident.id = :id")
    Accident getFetchAccidentById(@Param("id") Integer id);
}
