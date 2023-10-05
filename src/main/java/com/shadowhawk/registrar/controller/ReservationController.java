package com.shadowhawk.registrar.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shadowhawk.registrar.service.reservations.event.EventReservationService;

@RestController
@RequestMapping("api/reserve")
public class ReservationController {
    EventReservationService eventRegistrar = new EventReservationService();


	@GetMapping("/range")
	public String reserveRange() {
        return "Not setup yet!";
	}

	@GetMapping("/event")
    public String reserveEvent() {
        return "Not setup yet!";
    }
}
