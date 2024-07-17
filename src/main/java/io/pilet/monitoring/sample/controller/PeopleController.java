package io.pilet.monitoring.sample.controller;

import io.pilet.monitoring.sample.model.Person;
import io.pilet.monitoring.sample.service.PeopleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PeopleController {
    private final PeopleService peopleService;

    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @PostMapping("/people")
    Person create(@RequestBody Person person) {
        Person created = Person.of(person.name(), person.age(), person.zip());
        peopleService.create(created);
        return created;
    }

    @GetMapping("/people")
    List<Person> list() {
        return peopleService.list(100);
    }

    @GetMapping("/people/{id}")
    Person get(@PathVariable String id) {
        return peopleService.get(id);
    }

    @DeleteMapping("/people/{id}")
    void delete(@PathVariable String id) {
        peopleService.delete(id);
    }
}
