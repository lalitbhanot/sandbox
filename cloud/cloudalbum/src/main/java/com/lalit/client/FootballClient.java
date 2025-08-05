package com.lalit.client;

import com.lalit.model.Player;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient("FootballServer")
public interface FootballClient {
    @RequestMapping(method = RequestMethod.GET,
            value = "/players")
    List<Player> getPlayers();
}