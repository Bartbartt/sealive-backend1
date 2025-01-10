package org.example.service;

import org.example.model.Chatroom;
import org.example.model.SeaCreature;
import org.example.repository.ChatroomRepository;
import org.example.repository.SeaCreatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Service
public class ChatroomService {

    @Autowired
    private ChatroomRepository chatroomRepository;
    @Autowired
    private SeaCreatureRepository seaCreatureRepository;

    @Async
    public CompletableFuture<Chatroom> create(Chatroom chatroom){
            return CompletableFuture.supplyAsync(() -> chatroomRepository.save(chatroom));
    }

    @Async
    public CompletableFuture<Chatroom> createChatroomWithSeaCreatures (Chatroom chatroom, Set<Integer> seaCreatureIds) {
        Set<SeaCreature> seaCreatures = new HashSet<>(seaCreatureRepository.findAllById(seaCreatureIds));
        chatroom.setSeaCreatures(seaCreatures);
        return CompletableFuture.supplyAsync(() -> chatroomRepository.save(chatroom));
    }

    @Async
    public CompletableFuture<List<Chatroom>> getAll() {
        return CompletableFuture.supplyAsync(() -> chatroomRepository.findAll());
    }
}