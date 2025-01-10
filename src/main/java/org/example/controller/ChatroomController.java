package org.example.controller;
import jakarta.validation.Valid;
import org.example.model.Chatroom;
import org.example.model.SeaCreature;
import org.example.service.ChatroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("chatroom")
public class ChatroomController {

    @Autowired
    private ChatroomService chatroomService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;


    @PostMapping("create2")
    public CompletableFuture<ResponseEntity<Chatroom>> create2(@RequestBody Chatroom chatroom, @RequestParam(required = false) Set<Integer> seaCreatureIds) {
        return chatroomService.createChatroomWithSeaCreatures(chatroom, seaCreatureIds)
                .thenApply(createdChatroom -> new ResponseEntity<>(createdChatroom, HttpStatus.CREATED));
    }


    @GetMapping("getAll")
    public CompletableFuture<ResponseEntity<List<Chatroom>>> getAll() {
        return chatroomService.getAll()
                .thenApply(ResponseEntity::ok);
    }
}
