package com.email.emailapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.email.emailapi.model.EmailRequest;
import com.email.emailapi.service.EmailService;

@RestController
@CrossOrigin
public class EmalController {
	@Autowired
	private EmailService emailService;
	
	@RequestMapping("/email")
	public String welcome() {
		return "HElloe";
	}
	
	// ResponseEntity is meant to represent the entire HTTP response. You can control anything that goes into it: status code, headers, and body.
	@RequestMapping(value="/sendEmail", method=RequestMethod.POST)
	public ResponseEntity<?> sendEmail(@RequestBody EmailRequest request){
		System.out.println(request);
		boolean result = this.emailService.sendEmail(request.getSubject(), request.getMessage(), request.getTo());
//		System.out.println(request);
		if(result) {			
			return ResponseEntity.ok("Email sent successfully...");
		}else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Email not sent..");
		}
	}
}
