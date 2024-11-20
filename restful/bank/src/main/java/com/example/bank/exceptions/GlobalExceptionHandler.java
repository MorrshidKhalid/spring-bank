package com.example.bank.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<ErrorDetails> handleNotFoundException(NotFoundException e) {

        ErrorDetails errorDetails = new ErrorDetails(
                e.getMessage(),
                HttpStatus.NOT_FOUND,
                new Date());

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = NoContentException.class)
    public ResponseEntity<ErrorDetails> handelNoContentFoundException(NoContentException e) {

        ErrorDetails errorDetails = new ErrorDetails(
                        e.getMessage(),
                        HttpStatus.NO_CONTENT,
                        new Date());

        return new ResponseEntity<>(errorDetails, HttpStatus.NO_CONTENT);
    }


    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<ErrorDetails> handelBadRequestException(BadRequestException e) {

        ErrorDetails errorDetails = new ErrorDetails(
                        e.getMessage(),
                        HttpStatus.BAD_REQUEST,
                        new Date());

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(value = NotAcceptable.class)
    public ResponseEntity<ErrorDetails> handelNotAcceptableException(BadRequestException e) {

        ErrorDetails errorDetails = new ErrorDetails(
                e.getMessage(),
                HttpStatus.NOT_ACCEPTABLE,
                new Date());

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_ACCEPTABLE);
    }
}