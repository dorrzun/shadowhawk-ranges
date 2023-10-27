package com.shadowhawk.registrar.service.reservations.range;

import java.io.IOException;

/**
 * Implemented by a class that is responsible for 
 */

public interface ReservationSystem {
    /**
     * Create a reservation
     * @return The UID of the reservation
     */
    String createReservation(String time) throws IOException;
    String modifyReservation(String reservationId);
    String removeReservation(String reservationId);
}
