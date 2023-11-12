package com.shadowhawk.registrar.service.reservations.range;

import java.io.IOException;


/**
 * Implemented by a class responsible for facilitiating CRUD operations on a reservation system.
 */
public interface ReservationSystem {
    /**
     * Create a reservation and return the unique identifier (UID) for it to the user
     * @param request The range reservation request
     * @return A UID for the created reservation
     */
    String createReservation(RangeReservationRequest request) throws IOException, IllegalArgumentException;
    String deleteReservation(String reservationId) throws IOException, IllegalArgumentException;
}
