package ru.job4j.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.model.Accident;
import ru.job4j.model.AccidentType;
import ru.job4j.model.Rule;

import javax.persistence.GeneratedValue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class AccidentJdbcTemplate {
    private final JdbcTemplate jdbc;

    public AccidentJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Accident save(Accident accident, String [] ids) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement statement = connection.prepareStatement("insert into accident (name, type_id) values (?, ?);", Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, accident.getName());
                statement.setInt(2, accident.getType().getId());
                return statement;
            }
        }, keyHolder);
        for (String str : ids) {
            jdbc.update("insert into rules_accident (accident_id, rules_id) values (?, ?)",
                    keyHolder.getKeys().get("id"), Integer.parseInt(str));
        }
        return accident;
    }

    public List<Accident> getAll() {
        return jdbc.query("SELECT " +
                        "accident.id AS accident_id, " +
                        "accident.name as accident_name, " +
                        "types.id as types_id, " +
                        "types.name as types_name " +
                        "FROM accident " +
                        "         JOIN types " +
                        "              ON types.id = accident.type_id;",
                (rs, row) -> {
                    Accident accident = new Accident();
                    AccidentType accidentType = new AccidentType();
                    accident.setId(rs.getInt("accident_id"));
                    accident.setName(rs.getString("accident_name"));
                    accidentType.setId(rs.getInt("types_id"));
                    accidentType.setName(rs.getString("types_name"));
                    accident.setType(accidentType);
                    List<Rule> rules = jdbc.query("select * " +
                                    "from rules_accident " +
                                    "join rules " +
                                    " ON rules_accident.rules_id = rules.id " +
                                    "where rules_accident.accident_id = " + accident.getId() + ";",
                            (r,ro) -> {
                            Rule rule = new Rule();
                            rule.setId(r.getInt("id"));
                            rule.setName(r.getString("name"));
                            return rule;
                            });
                    accident.setRules(new HashSet<>(rules));
                    return accident;
                });
    }

    public List<AccidentType> getAllTypes() {
        return jdbc.query("select id, name from types",
                (rs, row) -> {
                    AccidentType type = new AccidentType();
                    type.setId(rs.getInt("id"));
                    type.setName(rs.getString("name"));
                    return type;
                });
    }

    public List<Rule> getAllRules() {
        return jdbc.query("select id, name from rules",
                (rs, row) -> {
                    Rule rule = new Rule();
                    rule.setId(rs.getInt("id"));
                    rule.setName(rs.getString("name"));
                    return rule;
                });
    }

    public Accident findById(Integer id) {
        return jdbc.queryForObject("SELECT " +
                        "accident.id AS accident_id, " +
                        "accident.name as accident_name, " +
                        "types.id as types_id, " +
                        "types.name as types_name " +
                        "FROM accident " +
                        "         JOIN types " +
                        "              ON types.id = accident.type_id" +
                        "where accident.id == ?;",
                (rs, row) -> {
                    Accident accident = new Accident();
                    AccidentType accidentType = new AccidentType();
                    accident.setId(rs.getInt("accident_id"));
                    accident.setName(rs.getString("accident_name"));
                    accidentType.setId(rs.getInt("types_id"));
                    accidentType.setName(rs.getString("types_name"));
                    accident.setType(accidentType);
                    List<Rule> rules = jdbc.query("select * " +
                                    "from rules_accident " +
                                    "join rules " +
                                    " ON rules_accident.rules_id = rules.id " +
                                    "where rules_accident.accident_id = " + accident.getId() + ";",
                            (r,ro) -> {
                                Rule rule = new Rule();
                                rule.setId(r.getInt("id"));
                                rule.setName(r.getString("name"));
                                return rule;
                            });
                    accident.setRules(new HashSet<>(rules));
                    return accident;
                }, id);
    }
}
