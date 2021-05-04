package ru.evteev.poll.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.evteev.poll.entity.Person;

import java.util.List;

@AllArgsConstructor
@Repository
public class PersonRepositoryImpl implements PersonRepository {

    private final SessionFactory sessionFactory;

    @Override

    public List<Person> getAllPersons() {
        Session session = sessionFactory.getCurrentSession();
        List<Person> personList = session.createQuery("from Person order by id", Person.class)
                .getResultList();
        return personList;
    }

    @Override
    public void createOrUpdatePerson(Person person) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(person);
    }

    @Override
    public Person getPerson(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Person.class, id);
    }

    @Override
    public void deletePerson(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query<Person> query = session.createQuery(
                "delete from Person where id = :personId");
        query.setParameter("personId", id);
        query.executeUpdate();
    }
}
