package com.bezkoder.esprit.controllers;


import com.bezkoder.esprit.models.ChargeRequest;
import com.bezkoder.esprit.models.PaymentResponse;
import com.bezkoder.esprit.services.StripeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private StripeService stripeService;

    @PostMapping("/charge")
    public ResponseEntity<PaymentResponse> charge(@RequestBody ChargeRequest chargeRequest) {
        try {
            PaymentResponse response = stripeService.charge(chargeRequest);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging purposes
            return ResponseEntity.internalServerError().build(); // More sophisticated error handling is recommended
        }
    }}