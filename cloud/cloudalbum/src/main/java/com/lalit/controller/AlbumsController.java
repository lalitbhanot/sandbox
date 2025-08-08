package com.lalit.controller;


import com.lalit.client.FootballClient;
import com.lalit.model.Player;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
    @RequestMapping("/albums")
    public class AlbumsController {
        private final FootballClient footballClient;

        public AlbumsController(FootballClient footballClient) {
            this.footballClient = footballClient;
        }

        @GetMapping("/players")
        public List<Player> getPlayers() {
            return footballClient.getPlayers();
        }

    @GetMapping
    public List<String> getAlbums(){
        return List.of("Album 1", "Album 2", "Album 3");

    }
}