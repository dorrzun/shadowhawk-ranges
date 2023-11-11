package com.shadowhawk.registrar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.shadowhawk.registrar.service.reservations.range.RangeReservationRequest;
import com.shadowhawk.registrar.service.reservations.range.ReservationSystem;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("api/reserve")
public class ReservationController {
    @Autowired ReservationSystem rangeReserver;

    @ResponseBody
    @Operation(description = "Reserve a range.")
    @ApiResponse(responseCode = "200", description = "Reservation made")
    @ApiResponse(responseCode = "400", description = "One reservation limit reached or date not available")
	@GetMapping("/range")
	public ResponseEntity<String> reserveRange(@RequestBody RangeReservationRequest req) {
        try{
            return ResponseEntity.ok(rangeReserver.createReservation(req));
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Failed to reserve");
	    }
    }
}
