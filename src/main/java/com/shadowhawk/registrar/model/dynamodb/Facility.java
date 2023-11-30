package com.shadowhawk.registrar.model.dynamodb;

import java.util.Map;

import lombok.Data;

/**
 * Represents a real world location that provides the various range services
 * Each facility has hours of operation and some other useful info
 */
public class Facility {
    /**
     * Tracks the general days/hours of availability
     * @apiNote Corresponding {@link Holiday} information takes precedence over this
     */
    private Map<Day,Availability> regularAvailability;

    //Tracks whether or not this facility is open on a certain holiday
    //Of the holidays it is open, what is their availability?
    private Map<Holiday,Availability> holidayAvailability;

    @Data
    private static class Availability {
        private Boolean isOpen;
        private Integer openTime;
        private Integer closeTime;
    }

    /**
     * Given a holiday that the facility is open on, get their hours of availability
     * @param h The holiday to check
     * @return
     */
    public Integer checkHoursForHoliday(final Holiday h){
        return holidayAvailability.get(h).openTime;
    }

    /**
     * Determines if this facility is open on the given holiday
     * @apiNote You are guaranteed a true/false value
     * @param h The holiday to check
     * @return 
     */
    public boolean isOpenOnHoliday(final Holiday h){
        return holidayAvailability.get(h).isOpen;
    }

    /**
     * Declaration of the Holidays
     * @apiNote These 
     */
    enum Holiday {
        THANKSGIVING, 
        CHRISTMAS, //1703480400 to 1703566799
    }

    enum Day {
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY,
        SUNDAY
    }
}
