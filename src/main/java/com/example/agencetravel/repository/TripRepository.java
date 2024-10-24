package com.example.agencetravel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.agencetravel.model.Trip;

public interface TripRepository extends JpaRepository<Trip, Long> {
}
