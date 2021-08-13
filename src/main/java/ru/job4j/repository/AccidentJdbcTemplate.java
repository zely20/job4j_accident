package ru.job4j.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.model.Accident;
import ru.job4j.model.AccidentType;
import ru.job4j.model.Rule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

//@Repository
public class AccidentJdbcTemplate {
    private final JdbcTemplate jdbc;
    private final AccidentMapper accidentMapper;
    private final static String SAVE_ACCIDENT = "INSERT INTO accident (name, type_id) VALUES (?, ?);";
    private final static String GET_ALL_TYPES = "SELECT id, name FROM types;";
    private final static String SAVE_RULES_ACCIDENT = "INSERT INTO rules_accident (accident_id, rules_id) VALUES (?, ?);";
    private final static String GET_ALL_ACCIDENT = "SELECT accident.id AS accident_id, accident.name AS accident_name, types.id AS types_id, types.name AS types_name FROM accident JOIN types ON types.id = accident.type_id;";
    private final static String GET_ALL_RULES = "SELECT id, name FROM rules;";
    private final static String GET_ACCIDENT_FIND_BY_ID = "SELECT accident.id AS accident_id, accident.name AS accident_name, types.id AS types_id, types.name AS types_name FROM accident JOIN types ON types.id = accident.type_id WHERE accident.id = ?;";
    private final static String UPDATE_ACCIDENT = "UPDATE accident SET name = (?), type_id = (?) WHERE id = (?);";

    public AccidentJdbcTemplate(JdbcTemplate jdbc, AccidentMapper accidentMapper) {
        this.jdbc = jdbc;
        this.accidentMapper = accidentMapper;
    }

    public Accident save(Accident accident, String [] ids) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement statement = connection.prepareStatement(SAVE_ACCIDENT, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, accident.getName());
                statement.setInt(2, accident.getType().getId());
                return statement;
            }
        }, keyHolder);
        for (String str : ids) {
            jdbc.update(SAVE_RULES_ACCIDENT,
                    keyHolder.getKeys().get("id"), Integer.parseInt(str));
        }
        return accident;
    }

    public List<Accident> getAll() {
        return jdbc.query(GET_ALL_ACCIDENT,
                accidentMapper);
    }

    public List<AccidentType> getAllTypes() {
        return jdbc.query(GET_ALL_TYPES,
                (rs, row) -> {
                    AccidentType type = new AccidentType();
                    type.setId(rs.getInt("id"));
                    type.setName(rs.getString("name"));
                    return type;
                });
    }

    public List<Rule> getAllRules() {
        return jdbc.query(GET_ALL_RULES,
                (rs, row) -> {
                    Rule rule = new Rule();
                    rule.setId(rs.getInt("id"));
                    rule.setName(rs.getString("name"));
                    return rule;
                });
    }

    public Accident findById(Integer id) {
        return jdbc.queryForObject(GET_ACCIDENT_FIND_BY_ID,
                accidentMapper, id);
    }

    public Accident update(Accident accident, String[] ids) {
        jdbc.update(UPDATE_ACCIDENT,
                accident.getName(),
                accident.getType().getId(),
                accident.getId());
        return accident;
    }

    public Integer delete(Integer id) {
        jdbc.update("DELETE FROM rules_accident WHERE accident_id = (?);", id);
        jdbc.update("DELETE FROM accident WHERE id = (?);", id);
        return id;
    }

    public List<Rule> findRulesById(Integer id) {
        return jdbc.query("SELECT * " +
                        "FROM rules_accident " +
                        "JOIN rules " +
                        " ON rules_accident.rules_id = rules.id " +
                        "WHERE rules_accident.accident_id = (?);",
                (rs,row) -> {
                    Rule rule = new Rule();
                    rule.setId(rs.getInt("id"));
                    rule.setName(rs.getString("name"));
                    return rule;
                }, id);
    }
}
