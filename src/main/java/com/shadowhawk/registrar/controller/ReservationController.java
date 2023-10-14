package com.shadowhawk.registrar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shadowhawk.registrar.service.reservations.range.ReservationSystem;

@RestController
@RequestMapping("api/reserve")
public class ReservationController {
    @Autowired ReservationSystem rangeRegistrar;


	@GetMapping("/range")
	public String reserveRange() {
        return "Not setup yet!";
	}

	@GetMapping("/event")
    public String reserveEvent() {
        return "Not setup yet!";
    }
}
