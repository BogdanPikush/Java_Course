package com.api.services;

import com.api.models.Thing;
import com.api.repositories.ThingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThingService {

    @Autowired
    private ThingRepository thingRepository;

    public Thing createThing(Thing thing) {
        return thingRepository.save(thing);
    }

    public Thing updateThing(String id, Thing updatedThing) {
        Thing existingThing = thingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Thing not found"));

        existingThing.setName(updatedThing.getName());
        existingThing.setDescription(updatedThing.getDescription());
        existingThing.setGive(updatedThing.getGive());

        return thingRepository.save(existingThing);
    }

    public Thing getThingById(String id) {
        return thingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Thing not found"));
    }

    public List<Thing> getThingsByUserId(String userId) {
        return thingRepository.findByIdUser(userId);
    }

    public void deleteThing(String id) {
        thingRepository.deleteById(id);
    }
}
