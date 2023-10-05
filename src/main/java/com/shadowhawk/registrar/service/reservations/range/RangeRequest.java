package com.shadowhawk.registrar.service.reservations.range;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * Represents the information submitted by a range member who would like to reserve
 * a shooting range at a certain time/day.
 */
@Data //This provides both @Getter and @Setter too
public class RangeRequest {
    private LocalDateTime timeOfRequest = LocalDateTime.now();
    /**
        A duration time/date (somewhat implemented above)
        Membership ID (Tells us who is booking the range)
        The range # they want to reserve (e.g: 1-30)
        Reservation type (Event/Personal)
        Additional info is TBD until I hear back from them.
     */
}
