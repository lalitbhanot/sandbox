package com.lalit.controller;

import java.util.List;

import com.lalit.dto.Playerv1;
import com.lalit.service.PlayersService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RequestMapping("/players")
@RestController
public class PlayersController {

    private PlayersService playersService;

    public PlayersController(PlayersService playersService) {
        this.playersService = playersService;
    }

    @GetMapping
    public List<Playerv1> getPlayers() {
        return playersService.getPlayers();
    }

    @GetMapping("/{id}")
    public Playerv1 getPlayer(@PathVariable int id) {
        return playersService.getPlayer(id);
    }

    @PostMapping
    public Playerv1 createPlayer(@RequestBody Playerv1 player) {
        return playersService.createPlayer(player);
    }
    
    
}
