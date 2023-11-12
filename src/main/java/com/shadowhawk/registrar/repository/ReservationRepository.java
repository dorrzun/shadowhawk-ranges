package com.shadowhawk.registrar.repository;

import java.util.Optional;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import com.shadowhawk.registrar.model.dynamodb.Reservation;

/**
 * Represents the data contract between us and the DynamoDB table for Reservations
 * {@link Reservation}
 */
@EnableScan
public interface ReservationRepository extends 
  CrudRepository<Reservation, String> {
    Optional<Reservation> findById(String id); //Find by Reservation Id
}

