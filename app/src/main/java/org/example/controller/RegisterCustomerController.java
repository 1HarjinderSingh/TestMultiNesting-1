package org.example.controller;

import org.example.dto.CustomerDTO;
import org.example.service.CustomerService;
import org.example.utils.CustomerValidationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/register")
public class RegisterCustomerController {

    private final CustomerService customerService;

    public RegisterCustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> register(@Valid @RequestBody CustomerDTO customer) {
        String firstName = customer.getFirstName().trim();
        boolean allowed = CustomerValidationUtils.isAllowedFirstName(firstName);

        if (allowed) {
            String message = customerService.customerSalutation(firstName, customer.getAddress());
            return ResponseEntity.ok(message);
        } else {
            String err = String.format("Error: %s is not authorized to register.", firstName);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);
        }
    }
}
