package com.april.bank.application.request;


import javax.validation.constraints.NotBlank;

public class CreateAccountRequest {

    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;

    public CreateAccountRequest(@NotBlank String firstName, @NotBlank String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
