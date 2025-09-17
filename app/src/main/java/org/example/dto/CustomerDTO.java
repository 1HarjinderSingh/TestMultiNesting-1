package org.example.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CustomerDTO {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    private boolean isActive;

    @NotNull
    @Valid
    private Address address;

    public CustomerDTO() {
    }

    public CustomerDTO(String firstName, String lastName, boolean isActive, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.isActive = isActive;
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", isActive=" + isActive +
                ", address=" + address +
                '}';
    }
}
