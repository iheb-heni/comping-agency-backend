package com.example.agencetravel.controller;

import com.example.agencetravel.model.Trip;
import com.example.agencetravel.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/trips")
public class TripController {

    @Autowired
    private TripRepository tripRepository;

    // Create a new trip
    @PostMapping
    public Trip createTrip(@RequestBody Trip trip) {
        return tripRepository.save(trip);
    }

    // Get all trips
    @GetMapping
    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }

    // Get a trip by ID
    @GetMapping("/{id}")
    public ResponseEntity<Trip> getTripById(@PathVariable Long id) {
        Optional<Trip> trip = tripRepository.findById(id);
        return trip.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update a trip by ID
    @PutMapping("/{id}")
    public ResponseEntity<Trip> updateTrip(@PathVariable Long id, @RequestBody Trip tripDetails) {
        Optional<Trip> tripOptional = tripRepository.findById(id);
        if (tripOptional.isPresent()) {
            Trip trip = tripOptional.get();
            trip.setDestination(tripDetails.getDestination());
            trip.setDepartureDate(tripDetails.getDepartureDate());
            trip.setPrice(tripDetails.getPrice());
            return ResponseEntity.ok(tripRepository.save(trip));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a trip by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrip(@PathVariable Long id) {
        if (tripRepository.existsById(id)) {
            tripRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
