package com.pm.patient_service.exception;

public class EmailAlreadyExistsExeption extends RuntimeException {
    public EmailAlreadyExistsExeption(String message) {
        super(message);
    }
}
