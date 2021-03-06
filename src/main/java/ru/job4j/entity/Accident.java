package ru.job4j.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "accident")
public class Accident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @OneToOne
    @JoinColumn(name = "type_id")
    private AccidentType type;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "rules_accident",
               joinColumns = @JoinColumn(name = "accident_id"),
                inverseJoinColumns = @JoinColumn(name = "rules_id"))
    private Set<Rule> rules = new HashSet<>();

    public Set<Rule> getRules() {
        return rules;
    }

    public void setRules(Set<Rule> rules) {
        this.rules = rules;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AccidentType getType() {
        return type;
    }

    public void setType(AccidentType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Accident accident = (Accident) o;
        return id == accident.id && Objects.equals(name, accident.name) && Objects.equals(type, accident.type) && Objects.equals(rules, accident.rules);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, rules);
    }

    public void addRule(Rule rule) {
        this.rules.add(rule);
    }
}
