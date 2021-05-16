package com.example.demo.services;

import com.example.demo.models.Person;
import com.example.demo.models.Relationship;
import com.example.demo.repositories.PersonRepository;
import com.example.demo.repositories.RelationshipRepository;
import com.example.demo.utils.Pair;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class RelationshipService {
    private final RelationshipRepository relationshipRepository;
    private final PersonRepository personRepository;

    public List<Relationship> getAllRelationships() {
        return relationshipRepository.findAll();
    }

    public void saveRecord(Relationship relationship) {
        relationshipRepository.save(relationship);
    }

    public List<Pair> mostConnected(int k) {
        List<Person> users = personRepository.findAll();

        List<Pair> pairs = new ArrayList<>();

//        Map<Person, Integer> map = new HashMap<>();
        for (var person: users) {
            Integer count = relationshipRepository.numberOfFriends(person.getId());
            pairs.add(new Pair(person, count));
        }

        Collections.sort(pairs);

        for (int index = k; index < pairs.size(); index++) {
            pairs.remove(pairs.get(index));
            index--;
        }
        return pairs;
    }

    public List<Pair> leastConnected(int k) {
        List<Person> users = personRepository.findAll();

        List<Pair> pairs = new ArrayList<>();

//        Map<Person, Integer> map = new HashMap<>();
        for (var person: users) {
            Integer count = relationshipRepository.numberOfFriends(person.getId());
            pairs.add(new Pair(person, count));
        }

        Collections.reverse(pairs);

        for (int index = k; index < pairs.size(); index++) {
            pairs.remove(pairs.get(index));
            index--;
        }
        return pairs;
    }
}
