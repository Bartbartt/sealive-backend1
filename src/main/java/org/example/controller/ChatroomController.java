package org.example.controller;
import org.example.model.Chatroom;
import org.example.service.ChatroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("chatroom")
public class ChatroomController {

    @Autowired
    private ChatroomService chatroomService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @PostMapping("create")
    public CompletableFuture<ResponseEntity<Chatroom>> create(@RequestBody Chatroom chatroom) {
        return chatroomService.create(chatroom)
                .thenApply(createdChatroom -> {
                    // Send the created chatroom to WebSocket subscribers
                    messagingTemplate.convertAndSend("/topic/chatrooms", createdChatroom);
                    return new ResponseEntity<>(createdChatroom, HttpStatus.CREATED);
                });
    }

    @GetMapping("getAll")
    public CompletableFuture<ResponseEntity<List<Chatroom>>> getAll() {
        return chatroomService.getAll()
                .thenApply(ResponseEntity::ok);
    }
}
