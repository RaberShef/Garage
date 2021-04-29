# Garage
Garage Spring Boot project

## How to run
Run the command below at the root directory of this project to start Spring Boot application.  
`.\mvnw spring-boot:run`

## How to call
To park a vehicle in the garage, send a POST request to the endpoint `http://localhost:8080/park`  
with a JSON request body like `{"type": "Car", "plate": "34-DSP-726", "color": "Red"}`

To unpark a vehicle, send a DELETE request to the endpoint `http://localhost:8080/leave`  
with a request param `ticketId`, which is an integer that starts from 1.

To check the status of the garage, send a GET request to the endpoint `http://localhost:8080/status`  

You can import and use the postman collection located in `src/main/resources/Garage.postman_collection.json`