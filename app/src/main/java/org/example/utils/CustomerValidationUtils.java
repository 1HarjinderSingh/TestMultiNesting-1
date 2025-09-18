package org.example.utils;

import org.example.AllowedCustomerName;

import java.util.Arrays;

public class CustomerValidationUtils {

    /**
     * Return true if the given firstName matches an allowed enum value (case-insensitive).
     */
    public static boolean isAllowedFirstName(String firstName) {
        if (firstName == null) return false;
        return Arrays.stream(AllowedCustomerName.values())
                .anyMatch(e -> e.name().equalsIgnoreCase(firstName.trim()));
    }
}
