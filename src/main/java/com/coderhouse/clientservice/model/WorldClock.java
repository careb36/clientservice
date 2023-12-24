package com.coderhouse.clientservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Model class representing the World Clock.
 * It captures various time-related details obtained from an external World Clock API.
 * This class is used to deserialize JSON data from the API into a Java object.
 * <p>
 * The class is annotated with @JsonIgnoreProperties to ignore any unknown properties
 * in the JSON response, ensuring only defined fields are mapped.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class WorldClock {

    // Attributes representing different components of the world clock time
    private String currentDateTime;       // Current date and time in UTC
    private String utcOffset;             // Offset from UTC
    private Boolean isDayLightSavingsTime; // Indicator of daylight saving time
    private String dayOfTheWeek;          // Current day of the week
    private String timeZoneName;          // Name of the time zone
    private Long currentFileTime;         // File time in long format
    private String ordinalDate;           // Date in ordinal format
    private String serviceResponse;       // Response from the World Clock service
}