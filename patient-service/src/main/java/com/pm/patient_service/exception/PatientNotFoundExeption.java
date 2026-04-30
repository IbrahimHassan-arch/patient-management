package com.pm.patient_service.exception;

public class PatientNotFoundExeption extends RuntimeException {
    public PatientNotFoundExeption(String message) {
        super(message);
    }
}
