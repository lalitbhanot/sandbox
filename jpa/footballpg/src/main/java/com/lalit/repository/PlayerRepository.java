package com.lalit.repository;

import com.lalit.entities.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PlayerRepository extends JpaRepository<PlayerEntity, Integer>
{
    List<PlayerEntity> findByDateOfBirth(LocalDate dateOfBirth);
    List<PlayerEntity> findByNameContaining(String name);
}
