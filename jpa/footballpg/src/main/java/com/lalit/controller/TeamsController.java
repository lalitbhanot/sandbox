package com.lalit.controller;

import java.util.List;

import com.lalit.dto.Teamv1;
import com.lalit.service.TeamsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/teams")
public class TeamsController {
    private TeamsService teamsService;

    public TeamsController(TeamsService teamsService) {
        this.teamsService = teamsService;
    }
    
    @GetMapping("/count")
    public int getTeamCount() {
        return teamsService.getTeamCount();
    }

    @GetMapping
    public List<Teamv1> getTeams() {
        return teamsService.getTeams();
    }

    @GetMapping("/{id}")
    public Teamv1 getTeam(@PathVariable int id) {
        return teamsService.getTeam(id);
    }
    
}
