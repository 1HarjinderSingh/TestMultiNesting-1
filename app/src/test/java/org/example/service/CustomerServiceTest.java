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
    public void validateMandatoryFields_whenNullCustomer_thenReturnCustomerPayload() {
        String missing = service.validateMandatoryFields(null);
        assertNotNull(missing);
        assertEquals("customer payload", missing);
    }

    @Test
    public void customerSalutation_whenValidInputs_thenReturnsFormattedString() {
        Address address = new Address("123", "Main St", "New York", "NY", "10001");
        String result = service.customerSalutation("Alice", address);
        assertEquals("Hello Alice from the city of New York", result);
    }

    @Test
    public void customerSalutation_whenNullCustomerName_thenReturnsGuestMessage() {
        Address address = new Address("123", "Main St", "New York", "NY", "10001");
        String result = service.customerSalutation(null, address);
        assertEquals("Hello Guest from an unknown city", result);
    }

    @Test
    public void customerSalutation_whenEmptyCustomerName_thenReturnsGuestMessage() {
        Address address = new Address("123", "Main St", "New York", "NY", "10001");
        String result = service.customerSalutation("", address);
        assertEquals("Hello Guest from an unknown city", result);
    }

    @Test
    public void customerSalutation_whenWhitespaceOnlyCustomerName_thenReturnsGuestMessage() {
        Address address = new Address("123", "Main St", "New York", "NY", "10001");
        String result = service.customerSalutation("   ", address);
        assertEquals("Hello Guest from an unknown city", result);
    }

    @Test
    public void customerSalutation_whenNullAddress_thenReturnsUnknownCityMessage() {
        String result = service.customerSalutation("Alice", null);
        assertEquals("Hello Alice from an unknown city", result);
    }

    @Test
    public void customerSalutation_whenNullCity_thenReturnsUnknownCityMessage() {
        Address address = new Address("123", "Main St", null, "NY", "10001");
        String result = service.customerSalutation("Alice", address);
        assertEquals("Hello Alice from an unknown city", result);
    }

    @Test
    public void customerSalutation_whenEmptyCity_thenReturnsUnknownCityMessage() {
        Address address = new Address("123", "Main St", "", "NY", "10001");
        String result = service.customerSalutation("Alice", address);
        assertEquals("Hello Alice from an unknown city", result);
    }

    @Test
    public void customerSalutation_whenWhitespaceOnlyCity_thenReturnsUnknownCityMessage() {
        Address address = new Address("123", "Main St", "   ", "NY", "10001");
        String result = service.customerSalutation("Alice", address);
        assertEquals("Hello Alice from an unknown city", result);
    }

    @Test
    public void customerSalutation_whenCustomerNameHasWhitespace_thenTrimsName() {
        Address address = new Address("123", "Main St", "Boston", "MA", "02101");
        String result = service.customerSalutation("  Bob  ", address);
        assertEquals("Hello Bob from the city of Boston", result);
    }

    @Test
    public void customerSalutation_whenCityHasWhitespace_thenTrimsCity() {
        Address address = new Address("123", "Main St", "  Chicago  ", "IL", "60601");
        String result = service.customerSalutation("Charlie", address);
        assertEquals("Hello Charlie from the city of Chicago", result);
    }
}
