package com.shadowhawk.registrar.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shadowhawk.registrar.model.BetterPerson;
import com.shadowhawk.registrar.model.Person;

/**
 * TODO:
 * 1. 
 */

/**
 * Represents the a simple REST API, where the Java application provides endpoints for anyone to hit!
 * 
 * These very endpoints service any and all HTTP requests sent to the app
 * This means that both the "front end" (the web page) can/will call these in order to get data,
 * but you could also just be someone running a little Python script fetching data too!
 * That's what's neat, it doesn't matter, and that's exactly what REST APIs are all about :)
 * 
 * @implNote It's designed to have security/authentication too, but we aren't worried about any of that just yet.
 */
@RestController
@RequestMapping("api/greetings")
public class GreetingController {
	@GetMapping("/greeting")
	public String defaultGreeting() {
		return "Greetings...This API stuff is pretty whack yo";
	}

	@GetMapping("/basicGreeting")
	public String greetByFirstName(@RequestParam String firstName) {
		//Make a Person or BetterPerson and greet them with the firstName
		return null;
	}
	@GetMapping("/advancedGreeting")
	public String greetByFullName(
		@RequestParam String firstName,
		@RequestParam String lastName) {
		return BetterPerson.builder().build().getBasicGreeting();
	}

}