package ru.evteev.poll.repository.poll_answers;

import ru.evteev.poll.entity.Person;

import java.util.List;

public interface PersonRepository {

    List<Person> getAllPersons();

    void createOrUpdatePerson(Person person);

    Person getPerson(int id);

    void deletePerson(int id);
}
