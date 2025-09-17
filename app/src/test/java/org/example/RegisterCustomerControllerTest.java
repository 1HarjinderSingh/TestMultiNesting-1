package org.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RegisterCustomer.class)
@Import(org.example.ValidationExceptionHandler.class)
public class RegisterCustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private org.example.service.CustomerService customerService;

    @Test
    public void givenAllowedName_whenRegister_thenReturnsWelcome() throws Exception {
        String json = "{\"firstName\":\"Alice\",\"lastName\":\"Smith\",\"isActive\":true,\"address\":{\"number\":\"123\",\"street\":\"Main St\",\"city\":\"Town\",\"state\":\"ST\",\"zipcode\":\"12345\"}}";
        org.mockito.Mockito.when(customerService.isAllowedFirstName("Alice")).thenReturn(true);

    mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(content().string("Welcome Alice! Your registration is accepted."));
    }

    @Test
    public void givenUnauthorizedName_whenRegister_thenReturnsForbidden() throws Exception {
        String json = "{\"firstName\":\"Mallory\",\"lastName\":\"Brown\",\"isActive\":true,\"address\":{\"number\":\"1\",\"street\":\"Nowhere\",\"city\":\"NoCity\",\"state\":\"NS\",\"zipcode\":\"00000\"}}";
        org.mockito.Mockito.when(customerService.isAllowedFirstName("Mallory")).thenReturn(false);

    mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isForbidden())
                .andExpect(content().string("Error: Mallory is not authorized to register."));
    }

    @Test
    public void givenMissingMandatoryFields_whenRegister_thenReturnsBadRequest() throws Exception {
        // missing firstName, lastName and address
        String json = "{\"firstName\":\"\",\"lastName\":\"\",\"isActive\":true}";

        mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Missing mandatory fields: firstName, lastName, address"));
    }

    @Test
    public void givenWhitespaceOnlyNames_thenValidationFails() throws Exception {
        String json = "{\"firstName\":\"   \",\"lastName\":\"  \",\"isActive\":true,\"address\":{\"number\":\"1\",\"street\":\"X\",\"city\":\"Y\",\"state\":\"S\",\"zipcode\":\"Z\"}}";

        mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("firstName")));
    }

    @Test
    public void givenPartialAddress_thenValidationFails() throws Exception {
        // address missing street
        String json = "{\"firstName\":\"Alice\",\"lastName\":\"Smith\",\"isActive\":true,\"address\":{\"number\":\"123\",\"street\":\"\",\"city\":\"Town\",\"state\":\"ST\",\"zipcode\":\"12345\"}}";

        mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("street")));
    }

    @Test
    public void givenVeryLongName_thenControllerHandles() throws Exception {
    String longName = String.join("", java.util.Collections.nCopies(1000, "A"));
    String json = String.format("{\"firstName\":\"%s\",\"lastName\":\"Smith\",\"isActive\":true,\"address\":{\"number\":\"123\",\"street\":\"Main\",\"city\":\"Town\",\"state\":\"ST\",\"zipcode\":\"12345\"}}", longName);

        // mock the service to return false (likely not in enum)
        org.mockito.Mockito.when(customerService.isAllowedFirstName(org.mockito.Mockito.anyString())).thenReturn(false);

        mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isForbidden());
    }
}
