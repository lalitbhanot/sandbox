package com.lalit.repository;

import java.util.List;
import java.util.Optional;

import com.lalit.entities.Team;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface TeamRepository extends MongoRepository<Team, String> {
    public Optional<Team> findByName(String name);

    public List<Team> findByNameContaining(String name);

    @Query(value = "{'players._id': ?0}", fields = "{'players.$': 1}")
    public Team findPlayerById(String id);

    @Query(value = "SELECT T FROM Team T WHERE T.name = ?0")
    public List<Team> findByNameSQL(String name);
}
