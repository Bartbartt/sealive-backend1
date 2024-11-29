package org.example.service;

import org.example.model.Account;
import org.example.model.Chatroom;
import org.example.repository.AccountRepository;
import org.example.repository.ChatroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class ChatroomService {

    @Autowired
    private ChatroomRepository chatroomRepository;

    @Async
    public CompletableFuture<Chatroom> create(Chatroom chatroom){

        return CompletableFuture.supplyAsync(() -> chatroomRepository.save(chatroom));
    }

    @Async
    public CompletableFuture<List<Chatroom>> getAll() {
        return CompletableFuture.supplyAsync(() -> chatroomRepository.findAll());
    }
}