package org.example.repository;
import org.example.model.Account;
import org.example.model.SeaCreature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeaCreatureRepository extends JpaRepository<SeaCreature, Integer> {
}