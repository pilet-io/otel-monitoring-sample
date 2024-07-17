package io.pilet.monitoring.sample.service;

import io.pilet.monitoring.sample.model.Person;
import io.pilet.monitoring.sample.repo.PeopleRepo;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class PeopleService {
    private final PeopleRepo peopleRepo;

    public PeopleService(PeopleRepo peopleRepo) {
        this.peopleRepo = peopleRepo;
    }

    public void create(Person person) {
        log.info("Creating person : {}", person);
        peopleRepo.create(person);
    }

    public Person get(String id) {
        Person person = peopleRepo.get(id);
        log.info("Getting person : {}", person);
        return person;
    }

    public List<Person> list(int limit) {
        List<Person> people = peopleRepo.list(limit);
        log.info("Listing people : {}", people.size());
        return people;
    }

    public void delete(String id) {
        log.info("Deleting person : {}", id);
        peopleRepo.delete(id);
    }
}
