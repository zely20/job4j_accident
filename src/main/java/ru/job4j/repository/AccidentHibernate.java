package ru.job4j.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.job4j.entity.Accident;
import ru.job4j.entity.AccidentType;
import ru.job4j.entity.Rule;

import java.util.List;

//@Repository
public class AccidentHibernate {

    private final SessionFactory sf;

    public AccidentHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    public Accident save(Accident accident) {
        try (Session session = sf.openSession()) {
            session.saveOrUpdate(accident);
            return accident;
        }
    }

    public List<AccidentType> getAllTypes() {
        try(Session session = sf.openSession()) {
            return session
                    .createQuery("from AccidentType", AccidentType.class)
                    .list();
        }
    }

    public List<Rule> getAllRules() {
        try(Session session = sf.openSession()) {
            return session
                    .createQuery("from Rule", Rule.class)
                    .list();
        }
    }

    public List<Accident> getAll() {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery("from Accident", Accident.class)
                    .list();
        }
    }

    public Accident findById(Integer id) {
        try (Session session = sf.openSession()) {
            return (Accident) session
                    .createQuery("select distinct ac from Accident ac "
                            + "join fetch ac.rules r "
                            + "join fetch ac.type t "
                            + "where ac.id = :sId")
                    .setParameter("sId", id)
                    .uniqueResult();
        }
    }

    public void delete(Integer id) {
        try (Session session = sf.openSession()) {
            Accident ac = session.load(Accident.class, id);
            session.delete(ac);
        }
    }
}
