package com.example.bank.log.log_projections;

import java.time.LocalDateTime;

public interface LogDTO {

    LocalDateTime getDateTime();
    String getAccountNo();
    String getMoveType();
    Double getAmount();
    String getCode();
}
