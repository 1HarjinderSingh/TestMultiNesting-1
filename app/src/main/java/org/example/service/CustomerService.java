package org.example.service;

import org.example.AllowedCustomerName;
import org.example.dto.CustomerDTO;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CustomerService {

    /**
     * Validate mandatory fields on the DTO. Returns null when OK, or a comma-separated list of missing fields.
     */
    public String validateMandatoryFields(CustomerDTO customer) {
        if (customer == null) return "customer payload";

        StringBuilder missing = new StringBuilder();
        if (customer.getFirstName() == null || customer.getFirstName().trim().isEmpty()) {
            missing.append("firstName");
        }
        if (customer.getLastName() == null || customer.getLastName().trim().isEmpty()) {
            if (missing.length() > 0) missing.append(", ");
            missing.append("lastName");
        }
        if (customer.getAddress() == null) {
            if (missing.length() > 0) missing.append(", ");
            missing.append("address");
        }

        return missing.length() > 0 ? missing.toString() : null;
    }

    /**
     * Return true if the given firstName matches an allowed enum value (case-insensitive).
     */
    public boolean isAllowedFirstName(String firstName) {
        if (firstName == null) return false;
        return Arrays.stream(AllowedCustomerName.values())
                .anyMatch(e -> e.name().equalsIgnoreCase(firstName.trim()));
    }
}
