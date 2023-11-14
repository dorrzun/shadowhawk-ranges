A proof of concept project that aims to provide a universal solution for all things 2A.

Core Offerings:
-Reserve a lane/range at any and all participating facilities.
-Register for events/classes, from the comfort of your home!

Additional Features:
-Fully customizable platform; structure and price in any way!


To install:
./mvnw install

To compile:
./mvnw compile

To run:
./mvnw spring-boot:run

To view the REST API via the built-in Swagger page:
http://localhost:8080/swagger-ui/index.html

To run DynamoDB locally, first download the v1 SDK for it (link coming soon):
Then, run the following commmand in the directory you extracted it to:
    java -jar -Djava.library.path=./DynamoDBLocal_lib -jar DynamoDBLocal.jar -sharedDb

 You can also view the database and perform operations on it through the AWS CLI they provide:
 aws dynamodb --endpoint-url http://localhost:8000 dynamodb


Made with love by Austin and Daniel <3<3<3
