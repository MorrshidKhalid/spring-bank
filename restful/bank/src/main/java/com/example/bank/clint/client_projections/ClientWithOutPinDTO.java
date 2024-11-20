package com.example.bank.clint.client_projections;

public interface ClientWithOutPinDTO {
        Long getId();
        String getAccountNo();
        String getFirstName();
        String getSecondName();
        String getLastName();
        String getEmail();
        Double getAccountBalance();
}