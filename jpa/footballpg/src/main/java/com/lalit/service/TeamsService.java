package com.lalit.service;

import java.util.List;

import com.lalit.entities.Team;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
@Service
public class TeamsService {

    private JdbcTemplate jdbcTemplate;

    public TeamsService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int getTeamCount() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM teams", Integer.class);
    }

    public Team getTeam(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM teams WHERE id = ?",
                new BeanPropertyRowMapper<>(Team.class), id);
    }

    public List<Team> getTeams() {
        return jdbcTemplate.query("SELECT * FROM teams", (rs, rowNum) -> {
            Team team = new Team();
            team.setId(rs.getInt("id"));
            team.setName(rs.getString("name"));
            return team;
        });
    }

}
