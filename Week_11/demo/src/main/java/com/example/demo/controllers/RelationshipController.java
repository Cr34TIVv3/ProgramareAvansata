package com.example.demo.controllers;

import com.example.demo.models.Person;
import com.example.demo.models.Relationship;
import com.example.demo.repositories.RelationshipRepository;
import com.example.demo.services.PersonService;
import com.example.demo.services.RelationshipService;
import com.example.demo.utils.Pair;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping(path = "/api/relationships"/*, method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}*/)
@AllArgsConstructor
public class RelationshipController {
    private final RelationshipService relationshipService;
    private final PersonService personService;

    @GetMapping
    public List<Relationship> findAll() {
        return relationshipService.getAllRelationships();
    }


    @GetMapping(path = "mostConnected/{k}")
    public List<Pair> mostConnected(@PathVariable int k) {
        return relationshipService.mostConnected(k);
    }

    @GetMapping(path = "leastConnected/{k}")
    public List<Pair> leastConnected(@PathVariable int k) {
        return relationshipService.leastConnected(k);
    }

    @PostMapping
    public ResponseEntity<String> save(@RequestBody String request) {
        JsonObject object = new JsonParser().parse(request).getAsJsonObject();
        int id1 = object.get("id1").getAsInt();
        int id2 = object.get("id2").getAsInt();


        try{
            var aux1 = personService.findOne(id1);
            var aux2 = personService.findOne(id2);

            if (aux1.getId() == 0 || aux2.getId() == 0) {
                return new ResponseEntity<>("Bad id's", HttpStatus.BAD_REQUEST);
            }
        }
        catch (Exception e) {
            return new ResponseEntity<>("Bad id's", HttpStatus.BAD_REQUEST);
        }



        Relationship relationship = new Relationship();
        relationship.setId1(id1);
        relationship.setId2(id2);
        relationshipService.saveRecord(relationship);
        return new ResponseEntity<>("Record created...", HttpStatus.OK);
    }
}
