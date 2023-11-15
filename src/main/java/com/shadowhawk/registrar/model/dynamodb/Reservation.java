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
    @JsonProperty
    @DynamoDBHashKey(attributeName = "id")
    private String id; //DynamoDB creates this value for you, do not change it

    @JsonProperty(required = true)
    @DynamoDBAttribute(attributeName = "orgId")
    private String orgId;

    @JsonProperty(required = true)
    @DynamoDBAttribute(attributeName = "timeCreated")
    private long timeCreated;

    @JsonProperty(required = true)
    @DynamoDBAttribute(attributeName = "memberId")
    private String memberId;

    @JsonProperty
    @DynamoDBAttribute(attributeName = "rangeNum")
    private Integer rangeNum;

}
