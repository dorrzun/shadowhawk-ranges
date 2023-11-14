package com.shadowhawk.registrar.model.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Represents an entry in our range reservation database
 */
@Data
@DynamoDBTable(tableName = "reservations")
public class Reservation {
    @DynamoDBHashKey(attributeName = "id")
    private String id;

    @DynamoDBAttribute(attributeName = "orgId")
    private String orgId;

    @DynamoDBAttribute(attributeName = "timeCreated")
    private long timeCreated;

    @DynamoDBAttribute(attributeName = "memberId")
    @JsonProperty(required = true)
    private String memberId;

    @DynamoDBAttribute(attributeName = "rangeNum")
    private Integer rangeNum;

    @DynamoDBAttribute(attributeName = "day")
    private Integer day;

    @DynamoDBAttribute(attributeName = "year")
    private Integer year;
}
