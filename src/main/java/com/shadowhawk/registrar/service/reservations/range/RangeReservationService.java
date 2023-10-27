package com.shadowhawk.registrar.service.reservations.range;

import java.io.IOException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * Responsible for processing, approving or rejecting range reservation requests
 */
@Slf4j
@Service
public class RangeReservationService implements ReservationSystem {

    @Override
    public String createReservation(String time) throws IOException {
        //validate input
        Optional<String> reservationId = Optional.empty();
        try{
           reservationId = makeReservation();
        }catch(IOException e){
            log.error("Failed to talk with database", e);
        }
        
        if (reservationId.isPresent()){
            return reservationId.get();
        }else{

        }

        throw new UnsupportedOperationException("Unimplemented method 'removeReservation'");
    }

    @Override
    public String modifyReservation(String reservationId) {
        throw new UnsupportedOperationException("Unimplemented method 'modifyReservation'");
    }

    @Override
    public String removeReservation(String reservationId) {
        return null;
    }
    
    private Optional<String> makeReservation() throws IOException {
        return Optional.empty();
    }
}
