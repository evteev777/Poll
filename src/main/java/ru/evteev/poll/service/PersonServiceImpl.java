package ru.evteev.poll.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.evteev.poll.entity.Person;
import ru.evteev.poll.repository.PersonRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    @Override
    @Transactional
    public List<Person> getAllPersons() {
        return personRepository.getAllPersons();
    }

    @Override
    @Transactional
    public void createOrUpdatePerson(Person person) {
        personRepository.createOrUpdatePerson(person);
    }

    @Override
    @Transactional
    public Person readPerson(int id) {
        return personRepository.getPerson(id);
    }

    @Override
    @Transactional
    public void deletePerson(int id) {
        personRepository.deletePerson(id);
    }
}
