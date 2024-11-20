package com.example.bank.trans.trans_inputs;

import com.example.bank.exceptions.BadRequestException;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record TrnasInput(
        @Positive
        @NotNull
        Long clientId,
        @NotNull
        Boolean moveType,
        @Positive
        double amount,
        @Positive
        @NotNull
        Long currencyId) {

    public TrnasInput {

        if (clientId <= 0)
            throw new BadRequestException("Not excepted Client id (" + clientId + ").");

        if (currencyId <= 0)
            throw new BadRequestException("Not excepted Currency id (" + currencyId + ").");

        if (amount <= 0)
            throw new BadRequestException("Not excepted data make sure (Amount) is positive and not (0).");

    }
}
