package com.shadowhawk.registrar.service.reservations.range;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * Responsible for processing, approving or rejecting range reservation requests
 */
@Slf4j
@Service
public class RangeReservationService implements ReservationSystem {
    private List<RangeReservationRequest> reservationDatabase;

    public RangeReservationService(){
        this.reservationDatabase = createDatabase();
    }

    /**
     * Creates a few requests
     */
    private List<RangeReservationRequest> createDatabase(){
        return new ArrayList<>(List.of(
            RangeReservationRequest.builder()
            .memberId("123")
            .reservationId("2000")
            .month(8)
            .day(25)
            .year(2023)
            .weaponType("rifle")
            .build(),
            
            RangeReservationRequest.builder()
            .memberId("007")
            .reservationId("1993")
            .month(7)
            .day(20)
            .year(2023)
            .weaponType("pistol")
            .build(),
            
            RangeReservationRequest.builder()
            .memberId("456")
            .reservationId("1997")
            .month(1)
            .day(1)
            .year(2023)
            .weaponType("pistol")
            .build()                       
        ));
    }

    /**
     * @implSpec Reserves a slot for the member, as long as they don't already have one with
     * their Membership ID and it is not on the same day as anyone else
     */
    @Override
    public String createReservation(RangeReservationRequest request) throws IOException, IllegalArgumentException {
        boolean hasExistingReservation = false; //Membership ID is already present in one of the reservations
        boolean hasDateConflict = false; //Membership ID is already present in one of the reservations
        
        log.info("Attempting to make reservation under Member ID: {}", request.getMemberId());

        //Loop through database and verify preconditions (no double reservations or date conflicts)
        for(int i = 0; i < reservationDatabase.size(); ++i){
            RangeReservationRequest currentDatabaseEntry = reservationDatabase.get(i);

            //Check for duplicate Membership ID
            if(currentDatabaseEntry.getMemberId().equals(request.getMemberId())){
                hasExistingReservation = true;
            }
            
            //Check for date conflict
            if(isSameDate(request, currentDatabaseEntry)){
                hasDateConflict = true;
            }
        }

        //If either condition is met, we cannot honor this request and need to raise an error
        if(hasExistingReservation || hasDateConflict){
            log.error("Member already has a reservation or there is a date conflict!");
            throw new IllegalArgumentException();
        }

        //"Confirm" the request by adding it to the database
        log.info("Reservation added under Member ID: {}", request.getMemberId());

        //Make the Reservation ID and add it to the reservation object
        String reservationId = UUID.randomUUID().toString();
        request.setReservationId(reservationId);        

        reservationDatabase.add(request);

        return reservationId;
    }

    private boolean isSameDate(RangeReservationRequest req1, RangeReservationRequest req2){
        boolean hasSameDay = req1.getDay().equals(req2.getDay());
        boolean hasSameMonth = req1.getMonth().equals(req2.getMonth());
        boolean hasSameYear = req1.getYear().equals(req2.getYear());

        return hasSameDay && hasSameMonth && hasSameYear;
    }

    @Override
    public String deleteReservation(String reservationId) throws IOException, IllegalArgumentException {
        String status = "Reservation not found.";

        for(int i = 0; i < reservationDatabase.size(); ++i){
            if(reservationDatabase.get(i).getReservationId().equals(reservationId)){
                reservationDatabase.remove(i);
                status = "Reservation deleted.";
            }
        }
        return status;
    }
}
