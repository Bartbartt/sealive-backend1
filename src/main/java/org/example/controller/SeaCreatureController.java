package org.example.controller;

import org.example.model.SeaCreature;
import org.example.service.SeaCreatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("seaCreature")
public class SeaCreatureController {

    @Autowired
    private SeaCreatureService seaCreatureService;

    @PostMapping("create")
    public CompletableFuture<ResponseEntity<SeaCreature>> create(@RequestBody SeaCreature seaCreature){
        try{
            return seaCreatureService.create(seaCreature)
                    .thenApply(createdSeaCreature -> new ResponseEntity<>(createdSeaCreature, HttpStatus.CREATED));
        }
        catch (Exception e){
            return CompletableFuture.completedFuture(new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT));
        }
    }

    @GetMapping("getAll")
    public CompletableFuture<ResponseEntity<List<SeaCreature>>> getAll(){
        return seaCreatureService.getAll()
                .thenApply(ResponseEntity::ok);
    }


}
