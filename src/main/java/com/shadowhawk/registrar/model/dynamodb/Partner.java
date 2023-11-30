package com.shadowhawk.registrar.model.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.fasterxml.jackson.annotation.JsonProperty;


public class Partner {
    /**
     * What business is being partnered with?
     */
    @JsonProperty
    @DynamoDBHashKey(attributeName = "businessId")
    private String businessId;
}
