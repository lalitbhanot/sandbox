package com.lalit.repository;

import com.lalit.entities.Card;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CardRepository extends MongoRepository<Card, String>{
    
}
