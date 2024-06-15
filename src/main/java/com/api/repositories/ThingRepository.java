package com.api.repositories;

import com.api.models.Thing;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ThingRepository extends MongoRepository<Thing, String> {
    List<Thing> findByIdUser(String idUser);
    Optional<Thing> findById(String id);
    void deleteById(String id);
}
