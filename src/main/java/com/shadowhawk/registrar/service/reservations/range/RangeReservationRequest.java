package com.shadowhawk.registrar.service.reservations.range;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

/**
 * Represents the information submitted by a range member who would like to reserve
 * a shooting range at a certain time/day.
 * 
 * @apiNote Requests must always have a Membership ID and the type of weapon that they will be using
 */
@Data
@Builder
public class RangeReservationRequest {
    private String reservationId; //Once approved, this holds the unique identifier to find this reservation
    @NonNull private String memberId; //The Membership ID for the person
    
    @NonNull private Integer month;
    @NonNull private Integer day;
    @NonNull private Integer year;
    private Integer rangeNumber; //The # of the range station that they want to reserve

    @NonNull private String weaponType; //"Pistol or Rifle"

    public boolean hasNoRangePreference(){
        return rangeNumber == null;
    }

    public boolean needsLongRange(){
        return weaponType.equalsIgnoreCase("Rifle");
    }
}
