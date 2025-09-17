package org.example.service;

import org.example.dto.Address;
import org.example.dto.CustomerDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerServiceTest {

    private final CustomerService service = new CustomerService();

    @Test
    public void validateMandatoryFields_whenMissingFields_thenReturnList() {
        CustomerDTO c = new CustomerDTO();
        String missing = service.validateMandatoryFields(c);
        assertNotNull(missing);
        assertTrue(missing.contains("firstName"));
        assertTrue(missing.contains("lastName"));
        assertTrue(missing.contains("address"));
    }

    @Test
    public void validateMandatoryFields_whenAllPresent_thenReturnNull() {
        Address a = new Address("1", "Main", "City", "ST", "12345");
        CustomerDTO c = new CustomerDTO("John", "Doe", true, a);
        String missing = service.validateMandatoryFields(c);
        assertNull(missing);
    }

    @Test
    public void isAllowedFirstName_whenAllowed_thenTrue() {
        assertTrue(service.isAllowedFirstName("Alice"));
        assertTrue(service.isAllowedFirstName("alice"));
    }

    @Test
    public void isAllowedFirstName_whenNotAllowed_thenFalse() {
        assertFalse(service.isAllowedFirstName("Mallory"));
        assertFalse(service.isAllowedFirstName(null));
    }

    @Test
    public void validateMandatoryFields_whenNullCustomer_thenReturnCustomerPayload() {
        String missing = service.validateMandatoryFields(null);
        assertNotNull(missing);
        assertEquals("customer payload", missing);
    }
}
