package com.shadowhawk.registrar.repository.cfg;

import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;

import lombok.extern.slf4j.Slf4j;

/**
 * DynamoDB Configurations are found here
 * @apiNote Please verify that the application.properties file is pointing to the correct 
 */
@Slf4j
@Configuration
@EnableDynamoDBRepositories(basePackages = "com.shadowhawk.registrar.repository")
public class DynamoDBConfig {
    @Value("${amazon.dynamodb.endpoint}")
    private String amazonDynamoDBEndpoint;

    @Value("${amazon.aws.accesskey}")
    private String amazonAWSAccessKey;

    @Value("${amazon.aws.secretkey}")
    private String amazonAWSSecretKey;

    private final String LOCAL_URL = "http://localhost:8000/";
    private final String REGION = "us-east-1";
    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
        AWSCredentials credentials = new BasicAWSCredentials(amazonAWSAccessKey, amazonAWSSecretKey);
        
        AmazonDynamoDB amazonDynamoDB;
        if(amazonDynamoDBEndpoint.equalsIgnoreCase(LOCAL_URL)){
            log.info("App is configured to use a LOCAL DynamoDB instance");
            amazonDynamoDB = AmazonDynamoDBClientBuilder.standard()
            .withEndpointConfiguration(new AmazonDynamoDBClientBuilder.EndpointConfiguration(amazonDynamoDBEndpoint, REGION))
            .withCredentials(new AWSStaticCredentialsProvider(credentials))
            .build();

            //Make the default tables, overwriting any existing ones
            String tableName = "reservations";
            if(amazonDynamoDB.listTables().getTableNames().contains(tableName)){
                amazonDynamoDB.deleteTable(tableName);
            }
            createTable(amazonDynamoDB, tableName);
            log.info("DynamoDB Tables found/created: {}", amazonDynamoDB.listTables());
        }else{
            log.info("App is configured to use an external DynamoDB instance");
            amazonDynamoDB = AmazonDynamoDBClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(REGION)
                .build();
        }
        return amazonDynamoDB;
    }

    @Bean
    public AWSCredentials amazonAWSCredentials() {
        return new BasicAWSCredentials(amazonAWSAccessKey, amazonAWSSecretKey);
    }

    private void createTable(AmazonDynamoDB amazonDynamoDB, String tableName) {
        CreateTableRequest createTableRequest = new CreateTableRequest()
                .withTableName(tableName)
                .withKeySchema(new KeySchemaElement("id", KeyType.HASH)) // Specify hash key
                .withAttributeDefinitions(new AttributeDefinition("id", ScalarAttributeType.S)) // Specify attribute type
                .withProvisionedThroughput(new ProvisionedThroughput(5L, 5L)); // Adjust provisioned throughput as needed

        amazonDynamoDB.createTable(createTableRequest);
        log.info("Table {} created successfully.", tableName);
    }
}
