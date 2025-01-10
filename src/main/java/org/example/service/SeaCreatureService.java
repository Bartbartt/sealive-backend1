package org.example.service;
import org.example.model.SeaCreature;
import org.example.repository.SeaCreatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class SeaCreatureService {

    @Autowired
    private SeaCreatureRepository seaCreatureRepository;

    @Async
    public CompletableFuture<SeaCreature> create(SeaCreature seaCreature){
        return CompletableFuture.supplyAsync(() -> seaCreatureRepository.save(seaCreature));
    }

    @Async
    public CompletableFuture<List<SeaCreature>> getAll() {
        return CompletableFuture.supplyAsync(() -> seaCreatureRepository.findAll());
    }
}