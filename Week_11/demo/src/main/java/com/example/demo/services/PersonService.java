package com.example.demo.services;

import com.example.demo.models.Person;
import com.example.demo.repositories.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PersonService {
    private final PersonRepository repository;
    public void savePerson(Person person) {
        repository.save(person);
    }
    public List<Person> getAllPersons() {
        return repository.findAll();
    }
    public boolean exists(int id) {
        return repository.existsById(id);
    }

    public Person findOne(int id) {
        return repository.getOne(id);
    }

    public void delete(int id) {
        repository.delete(repository.getOne(id));
    }

}
