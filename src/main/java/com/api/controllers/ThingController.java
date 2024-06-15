package com.api.controllers;

import com.api.models.Thing;
import com.api.services.ThingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/things")
public class ThingController {

    @Autowired
    private ThingService thingService;

    @PostMapping("/create")
    public ResponseEntity<Thing> createThing(@RequestBody Thing thing) {
        Thing createdThing = thingService.createThing(thing);
        return ResponseEntity.ok(createdThing);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Thing> updateThing(@PathVariable String id, @RequestBody Thing updatedThing) {
        Thing updated = thingService.updateThing(id, updatedThing);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Thing> getThingById(@PathVariable String id) {
        Thing thing = thingService.getThingById(id);
        return ResponseEntity.ok(thing);
    }

    @GetMapping("/getByUser/{userId}")
    public ResponseEntity<List<Thing>> getThingsByUserId(@PathVariable String userId) {
        List<Thing> things = thingService.getThingsByUserId(userId);
        return ResponseEntity.ok(things);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteThing(@PathVariable String id) {
        try {
            thingService.deleteThing(id);
            return ResponseEntity.ok("Thing deleted successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
