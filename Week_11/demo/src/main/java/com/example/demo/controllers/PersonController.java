package com.example.demo.controllers;

import com.example.demo.models.Person;
import com.example.demo.services.PersonService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javassist.tools.web.BadHttpRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/persons"/*, method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}*/)
@AllArgsConstructor
public class PersonController {
    final private PersonService personService;

    @GetMapping
    public List<Person> findAll() {
        return personService.getAllPersons();
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody String request) {
        JsonObject object = new JsonParser().parse(request).getAsJsonObject();
        String name = object.get("name").getAsString();
        Person person = new Person();
        person.setName(name);
        personService.savePerson(person);

        return new ResponseEntity<>("Record created...", HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<String> update(@RequestBody String request, @PathVariable Integer id) throws BadHttpRequest {
        if (personService.exists(id)) {
            JsonObject object = new JsonParser().parse(request).getAsJsonObject();
            String name = object.get("name").getAsString();

            Person person = personService.findOne(id);
            person.setName(name);
            personService.savePerson(person);
        } else {
            throw new BadHttpRequest();
        }
        return new ResponseEntity<>("Record updated successfully...", HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
        personService.delete(id);
        return new ResponseEntity<>("Record has been deleted..", HttpStatus.OK);
    }
}
