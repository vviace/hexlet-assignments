package exercise.controller;

import exercise.model.Person;
import exercise.repository.PersonRepository;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/people")

public class PeopleController {

    // Автоматически заполняем значение поля
    private final PersonRepository personRepository;

    public PeopleController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping(path = "/{id}")
    public Person getPerson(@PathVariable long id) {
        return personRepository.findById(id).orElseThrow();
    }

    @GetMapping(path = "")
    public Iterable<Person> getPeople() {
        return this.personRepository.findAll();
    }

    // BEGIN
    @PostMapping
    public Person savePerson(@RequestBody Person person) {
        Person personRep = personRepository.save(person);
        return personRep;
    }

    @DeleteMapping(path = "/{id}")
    public void deletePerson(@PathVariable long id) {
        personRepository.deleteById(id);
    }

    @PatchMapping(path = "/{id}")
    public Person updatePerson(@RequestBody Person person, @PathVariable long id) {
        Person personDb = personRepository.findById(id).orElseThrow();
        personDb.setFirstName(person.getFirstName());
        personDb.setLastName(person.getLastName());
        return personRepository.save(personDb);
    }
    // END
}
