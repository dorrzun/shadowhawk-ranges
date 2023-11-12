package com.shadowhawk.registrar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shadowhawk.registrar.model.dynamodb.Reservation;
import com.shadowhawk.registrar.repository.ReservationRepository;
import com.shadowhawk.registrar.service.reservations.range.RangeReservationRequest;
import com.shadowhawk.registrar.service.reservations.range.ReservationSystem;

import java.util.Optional;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/range-reservations")
public class ReservationController {
    @Autowired private ReservationSystem rangeReserver;
    @Autowired private ReservationRepository dynamoDbTable;

    @ResponseBody
    @Operation(description = "Reserve a range")
    @ApiResponse(responseCode = "200", description = "Reservation made")
    @ApiResponse(responseCode = "400", description = "One reservation limit reached or date not available")
	@PostMapping("/create")
	public ResponseEntity<String> reserveRange(@RequestBody RangeReservationRequest req) {
        try{
            return ResponseEntity.ok(rangeReserver.createReservation(req));
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Failed to reserve");
	    }
    }

    @Operation(description = "Delete a reservation")
    @ApiResponse(responseCode = "200", description = "Reservation made")
    @ApiResponse(responseCode = "400", description = "One reservation limit reached or date not available")
    @DeleteMapping(value = "/delete")
	public ResponseEntity<String> deleteReservation(@RequestParam String reservationId) {
        try{
            log.info("User gave {} for ID", reservationId);
            return ResponseEntity.ok().body(rangeReserver.deleteReservation(reservationId));
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Failed to delete anything");
        }
    }

    @Operation(description = "Add a Reservation to DynamoDB")
    @ApiResponse(responseCode = "200", description = "Item added")
    @ApiResponse(responseCode = "400", description = "Failed to add")
    @PutMapping(value = "/addItem")
	public ResponseEntity<Void> addItem(@RequestBody Reservation reservation) {
        String reservationId;
        try{
            log.info("Adding this reservation to the database {}", reservation);
            reservationId = dynamoDbTable.save(reservation).getId();
            log.info("Added. ID is {}", reservationId);
            return ResponseEntity.ok().build();
        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(description = "Find a Reservation DynamoDB")
    @ApiResponse(responseCode = "200", description = "Item found")
    @ApiResponse(responseCode = "400", description = "Error")
    @ApiResponse(responseCode = "404", description = "Not Found")
    @GetMapping(value = "/findItem")
	public ResponseEntity<Reservation> findItem(@RequestParam String reservationId) {
        try{
            log.info("Finding reservation with ID {} in the database", reservationId);
            Optional<Reservation> result = dynamoDbTable.findById(reservationId);
            if(result.isPresent()){
                return ResponseEntity.ok(result.get());
            }
            return ResponseEntity.notFound().build();
        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(description = "Delete a reservation DynamoDB")
    @ApiResponse(responseCode = "200", description = "delete added")
    @ApiResponse(responseCode = "400", description = "Failed to delete")
    @DeleteMapping(value = "/deleteItem")
	public ResponseEntity<Void> deleteItem(@RequestParam String reservationId) {
        try{
            log.info("Deleting this reservation from the database {}", reservationId);
            dynamoDbTable.deleteById(reservationId);
            log.info("Deleted reservation {}", reservationId);
            return ResponseEntity.ok().build();
        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}
