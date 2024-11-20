package com.example.bank.exceptions;

import org.springframework.http.HttpStatus;

import java.util.Date;

public record ErrorDetails(String message, HttpStatus httpStatus, Date timestamp) {
}
