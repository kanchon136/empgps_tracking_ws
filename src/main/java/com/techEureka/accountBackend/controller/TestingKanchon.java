package com.techEureka.accountBackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestingKanchon {

	@GetMapping("/testapi")
	public String testing() {
		return "Authentication Successs......!!!";
	}

}
