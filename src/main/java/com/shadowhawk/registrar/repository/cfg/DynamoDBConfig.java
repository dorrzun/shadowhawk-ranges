package com.shadowhawk.registrar.repository.cfg;

import java.util.List;

import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.local.main.ServerRunner;
import com.amazonaws.services.dynamodbv2.local.server.DynamoDBProxyServer;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;

import jakarta.validation.constraints.Size;
import lombok.extern.slf4j.Slf4j;

/**
 * DynamoDB Configurations are found here
 * @apiNote Please verify that the application.properties file is pointing to the correct 
 */
@Slf4j
@Configuration
@EnableDynamoDBRepositories(basePackages = "com.shadowhawk.registrar.repository")
public class DynamoDBConfig {
    @Value("${amazon.dynamodb.run-locally}")
    private Boolean runningLocally;

    @Value("${amazon.aws.accesskey}")
    private String amazonAWSAccessKey;

    @Value("${amazon.aws.secretkey}")
    private String amazonAWSSecretKey;

    private static final List<String> tables = List.of("reservations");

    private static final String LOCAL_SERVER_PORT = "8000";
    private static final String LOCAL_URL = "http://localhost:" + LOCAL_SERVER_PORT + "/";
    private static final String REGION = "us-east-1";

    /**
     * Creates the DynamoDB "client" that our app will use to communicate with the tables
     * @return
     * @throws Exception If the locally hosted server (if it's enabled) failed to start
     */
    @Bean
    public AmazonDynamoDB amazonDynamoDB() throws Exception {
        log.info("Connecting to DynamoDB on {} server", runningLocally ? "LOCAL" : REGION);
        AWSCredentials credentials = new BasicAWSCredentials(amazonAWSAccessKey, amazonAWSSecretKey);

        AmazonDynamoDB amazonDynamoDB;
        if(runningLocally){
            try{
                DynamoDBProxyServer server = ServerRunner.createServerFromCommandLineArgs(new String[]{ "-inMemory", "-port", LOCAL_SERVER_PORT});
                server.start();
            }catch(Exception e){
                log.info("Failed to start local DynamoDB server!");
            }

            amazonDynamoDB = AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AmazonDynamoDBClientBuilder.EndpointConfiguration(LOCAL_URL, REGION))
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();
        }else{
            amazonDynamoDB = AmazonDynamoDBClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(REGION)
                .build();
        }
        createTables(amazonDynamoDB, tables);
        log.info("DynamoDB Tables detected: {}", amazonDynamoDB.listTables());
        return amazonDynamoDB;
    }

    @Bean
    public AWSCredentials amazonAWSCredentials() {
        return new BasicAWSCredentials(amazonAWSAccessKey, amazonAWSSecretKey);
    }

    /**
     * Creates the DynamoDB tables with the pre-defined list of naems
     * @implNote All tables will be configured with the default throughput/generic attributes
     * @param amazonDynamoDB
     * @param tableNames
     */
    private void createTables(
        AmazonDynamoDB amazonDynamoDB,
        @Size(min = 1) List<String> tableNames) {
        tableNames.forEach(name -> {
        amazonDynamoDB.deleteTable(name);
        CreateTableRequest createTableRequest = new CreateTableRequest()
                .withTableName(name)
                .withKeySchema(new KeySchemaElement("id", KeyType.HASH)) // Specify hash key
                .withAttributeDefinitions(new AttributeDefinition("id", ScalarAttributeType.S)) // Specify attribute type
                .withProvisionedThroughput(new ProvisionedThroughput(5L, 5L)); // Adjust provisioned throughput as needed

        amazonDynamoDB.createTable(createTableRequest);
        log.info("Created Table {}", name);
        });
    }
}
