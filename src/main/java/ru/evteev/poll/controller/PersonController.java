package ru.evteev.poll.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.evteev.poll.dto.api.respomce.PersonDTO;
import ru.evteev.poll.dto.mapper.PersonMapper;
import ru.evteev.poll.entity.Person;
import ru.evteev.poll.exception.FieldValidationException;
import ru.evteev.poll.exception.NoSuchEntityException;
import ru.evteev.poll.service.PersonService;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
@Secured("ROLE_ADMIN")
public class PersonController {

    private static final String NO_SUCH_PERSON = "No such person with ID=%s in database";
    private static final String DELETED = "Person with ID=%s is deleted";

    private final PersonService personService;

    @GetMapping("/persons")
    public List<PersonDTO> getPersonList() {
        return personService.getAllPersons().stream()
                .map(PersonMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/persons/{id}")
    public PersonDTO getPerson(@PathVariable int id) {
        throwExceptionIfEmpty(id);
        Person person = personService.readPerson(id);
        return PersonMapper.INSTANCE.toDTO(person);
    }

    @PostMapping("/persons")
    public PersonDTO createPerson(@Valid @RequestBody Person person, BindingResult br) {
        br.getAllErrors().forEach(System.out::println);
        throwExceptionIfValidationFails(br);
        personService.createOrUpdatePerson(person);
        return PersonMapper.INSTANCE.toDTO(person);
    }

    @PutMapping("/persons")
    public PersonDTO updatePerson(@Valid @RequestBody Person person, BindingResult br) {
        throwExceptionIfValidationFails(br);
        personService.createOrUpdatePerson(person);
        return PersonMapper.INSTANCE.toDTO(person);
    }

    @DeleteMapping("/persons/{id}")
    public String deletePerson(@PathVariable int id) {
        throwExceptionIfEmpty(id);
        personService.deletePerson(id);
        return String.format(DELETED, id);
    }

    private void throwExceptionIfEmpty(@PathVariable int id) {
        Person person = personService.readPerson(id);
        if (person == null) {
            throw new NoSuchEntityException(
                    String.format(NO_SUCH_PERSON, id));
        }
    }

    private void throwExceptionIfValidationFails(BindingResult br) {
        if (br.hasErrors()) {
            throw new FieldValidationException(br.getAllErrors().toString());
        }
    }
}
