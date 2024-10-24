package com.example.agencetravel.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.agencetravel.model.Trip;
import com.example.agencetravel.repository.TripRepository;

@Service
public class TripService {
    @Autowired
    private TripRepository tripRepository;
    
    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }

    public Trip createTrip(Trip trip) {
        return tripRepository.save(trip);
    }

    public Trip updateTrip(Long id, Trip tripDetails) {
        Trip trip = tripRepository.findById(id).orElseThrow(() -> new RuntimeException("Trip not found"));
        trip.setDestination(tripDetails.getDestination());
        trip.setDepartureDate(tripDetails.getDepartureDate());
        trip.setPrice(tripDetails.getPrice());
        return tripRepository.save(trip);
    }

    public void deleteTrip(Long id) {
        tripRepository.deleteById(id);
    }
}
