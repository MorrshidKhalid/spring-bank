package com.example.bank.clint.client_inputs;

import com.example.bank.exceptions.BadRequestException;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;


public record ClientInput(
        @NotEmpty String accountNo,
        @NotEmpty String pinCode,
        @NotEmpty String firstName,
        @NotEmpty String secondName,
        @NotEmpty String lastName,
        @Email(message = "Email must be a valid email address.")
        String email,
        @Positive Double accountBalance
        ) {

    public ClientInput {
                boolean isEmpty = accountNo.trim().isEmpty() ||
                                  pinCode.trim().isEmpty() ||
                                  firstName.trim().isEmpty() ||
                                  secondName.trim().isEmpty() ||
                                  lastName.trim().isEmpty();

        if (isEmpty)
            throw new BadRequestException("Account Info must be satisfied nor fields Can be empty.");

        if (accountBalance < 0)
            throw new BadRequestException("Only positive values are allowed.");
    }
}