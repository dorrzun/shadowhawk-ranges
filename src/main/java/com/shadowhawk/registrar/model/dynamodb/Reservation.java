package com.shadowhawk.registrar.model.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBVersionAttribute;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Represents an entry in our range reservation database
 */
@Data
@DynamoDBTable(tableName = "reservations")
public class Reservation {
    @JsonProperty
    @DynamoDBHashKey(attributeName = "id")
    private String id;

    /**
     * User Account ID that created this reservation
     */
    @JsonProperty(required = true)
    @DynamoDBAttribute(attributeName = "accountId")
    private String accountId;

    /**
     * What business is this reservation made with?
     */
    @JsonProperty(required = true)
    @DynamoDBAttribute(attributeName = "businessId")
    private String businessId;

    /**
     * What facility is this reservation booked at?
     */
    @JsonProperty(required = true)
    @DynamoDBAttribute(attributeName = "facilityId")
    private String facilityId;

    @JsonProperty(required = true)
    @DynamoDBAttribute(attributeName = "timeCreated")
    private Long timeCreated;

    @DynamoDBVersionAttribute
    @JsonProperty(required = true)
    @DynamoDBAttribute(attributeName = "lastModified")
    private Long lastModified;

    @JsonProperty
    @DynamoDBAttribute(attributeName = "rangeNum")
    private Integer rangeNum;

}
