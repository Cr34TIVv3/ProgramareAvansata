package com.example.demo.repositories;

import com.example.demo.models.Relationship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RelationshipRepository extends JpaRepository<Relationship, Integer> {
    @Query("Select COUNT(R.id) FROM Relationship R WHERE R.id1 = ?1 OR R.id2 = ?1")
    int numberOfFriends(int id);
}
