package com.lalit.controller;


import com.lalit.exception.AlreadyExistsException;
import com.lalit.exception.NotFoundException;
import com.lalit.model.Player;
import com.lalit.service.FootballService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/players")
public class PlayerController {

         private FootballService footballService ;

    public PlayerController(FootballService footballService) {
        this.footballService = footballService;
    }

    @GetMapping
    public List<Player> listPlayers() {
        return footballService.listPlayers();
    }

    @PostMapping
    public void createPlayer(@RequestBody Player player) {
        footballService.addPlayer(player);
    }

//    @GetMapping("/{id}")
//    public Player readPlayer(@PathVariable String id) {
//        return footballService.getPlayer(id);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<Player> readPlayer(@PathVariable String id)
    {

            Player player = footballService.getPlayer(id);
            return new ResponseEntity<>(player, HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public void deletePlayer(@PathVariable String id) {
        footballService.deletePlayer(id);
    }

    @PutMapping("/{id}")
    public void updatePlayer(@PathVariable String id,
                             @RequestBody Player player) {
        footballService.updatePlayer(player);
    }


    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Not found")
    @ExceptionHandler(NotFoundException.class)
    public void notFoundHandler() {
    }
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Already exists")
    @ExceptionHandler(AlreadyExistsException.class) public void alreadyExistsHandler() {
    }
}