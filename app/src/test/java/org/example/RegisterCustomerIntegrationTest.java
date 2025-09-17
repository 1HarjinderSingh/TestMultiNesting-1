package org.example;

import org.example.dto.Address;
import org.example.dto.CustomerDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RegisterCustomerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void givenAllowedName_whenRegister_thenReturnsWelcome() {
        Address address = new Address("1", "Main", "Town", "ST", "12345");
        CustomerDTO c = new CustomerDTO("Alice", "Smith", true, address);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<CustomerDTO> entity = new HttpEntity<>(c, headers);

        ResponseEntity<String> resp = restTemplate.postForEntity("http://localhost:" + port + "/register", entity, String.class);
        assertEquals(200, resp.getStatusCodeValue());
        assertEquals("Welcome Alice! Your registration is accepted.", resp.getBody());
    }
}
