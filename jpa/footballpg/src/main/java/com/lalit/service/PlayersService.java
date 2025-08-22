package com.lalit.service;

import com.lalit.dto.Playerv1;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayersService {
        private JdbcClient jdbcClient;
        public PlayersService(JdbcClient jdbcClient) {
            this.jdbcClient = jdbcClient;
        }


    public List<Playerv1> getPlayers() {
        return jdbcClient.sql("SELECT * FROM players")
                .query(Playerv1.class)
                .list();
    }

    public Playerv1 getPlayer(int id) {
        return jdbcClient.sql("SELECT * FROM players WHERE id = :id")
                .param("id", id)
                .query(Playerv1.class)
                .single();
    }

    public Playerv1 createPlayer(Playerv1 player) {
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql("""
INSERT INTO players (jersey_number, name, position, date_of_birth,
team_id)
VALUES (:jersey_number, :name, :position, :date_of_birth, :team_id)
""")
                .param("name", player.getName())
                .param("jersey_number", player.getJerseyNumber())
                .param("position", player.getPosition())
                .param("date_of_birth", player.getDateOfBirth())
                .param("team_id", player.getTeamId())
                .update(keyHolder, "id");
        player.setId(keyHolder.getKey().intValue());
        return player;
    }
    }

