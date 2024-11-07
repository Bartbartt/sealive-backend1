package org.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("a")
public class HomeController {

    @RequestMapping("b")
    public String index(){
        return "Hallo welkom bij SeaLive/blueBottle/greenBottledb deze api is mementeel onder constructie kom later terug dan is hij waarschijnlijk nog steeds onder constructie";
    }

}
