package org.example.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerValidationUtilsTest {

    @Test
    public void isAllowedFirstName_whenAllowed_thenTrue() {
        assertTrue(CustomerValidationUtils.isAllowedFirstName("Alice"));
        assertTrue(CustomerValidationUtils.isAllowedFirstName("alice"));
        assertTrue(CustomerValidationUtils.isAllowedFirstName("BOB"));
        assertTrue(CustomerValidationUtils.isAllowedFirstName("Charlie"));
        assertTrue(CustomerValidationUtils.isAllowedFirstName("DEVIN"));
        assertTrue(CustomerValidationUtils.isAllowedFirstName("eve"));
    }

    @Test
    public void isAllowedFirstName_whenNotAllowed_thenFalse() {
        assertFalse(CustomerValidationUtils.isAllowedFirstName("Mallory"));
        assertFalse(CustomerValidationUtils.isAllowedFirstName("John"));
        assertFalse(CustomerValidationUtils.isAllowedFirstName("Jane"));
    }

    @Test
    public void isAllowedFirstName_whenNull_thenFalse() {
        assertFalse(CustomerValidationUtils.isAllowedFirstName(null));
    }

    @Test
    public void isAllowedFirstName_whenEmpty_thenFalse() {
        assertFalse(CustomerValidationUtils.isAllowedFirstName(""));
    }

    @Test
    public void isAllowedFirstName_whenWhitespaceOnly_thenFalse() {
        assertFalse(CustomerValidationUtils.isAllowedFirstName("   "));
    }

    @Test
    public void isAllowedFirstName_whenWithWhitespace_thenTrimsAndChecks() {
        assertTrue(CustomerValidationUtils.isAllowedFirstName("  Alice  "));
        assertTrue(CustomerValidationUtils.isAllowedFirstName("\tBob\n"));
        assertFalse(CustomerValidationUtils.isAllowedFirstName("  Mallory  "));
    }
}
