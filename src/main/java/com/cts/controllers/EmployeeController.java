package com.cts.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @GetMapping("/employee")
	public String sayHelloToEmployee() {
		return "Hello Employee";
	}
    
    @PostMapping("/employee")
    public ResponseEntity<?> showEmployeeDashboard() {
		return null;
    }
}
