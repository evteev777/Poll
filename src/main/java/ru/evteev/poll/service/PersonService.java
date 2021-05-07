package ru.evteev.poll.service;

import ru.evteev.poll.entity.Person;

import java.util.List;

public interface PersonService {

    List<Person> getAllPersons();

    void createOrUpdatePerson(Person person);

    Person getPerson(int id);

    void deletePerson(int id);
}
