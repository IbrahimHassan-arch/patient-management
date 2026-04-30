package com.pm.patient_service.mapper;

import com.pm.patient_service.dto.PatientRequestDTO;
import com.pm.patient_service.dto.PatientResponceDTO;
import com.pm.patient_service.model.Patient;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;

public class PatientMapper {
    public static PatientResponceDTO toDTO(@NotNull Patient patient){
        PatientResponceDTO patientDTO = new PatientResponceDTO();
        patientDTO.setId(patient.getId().toString());
        patientDTO.setName(patient.getName());
        patientDTO.setAdress(patient.getAddress());
        patientDTO.setEmail(patient.getEmail());
        patientDTO.setDateOfBirth(patient.getDateOfBirth().toString());
        return patientDTO;
    }

    public static Patient toModel(PatientRequestDTO patientRequestDTO){
        Patient patient = new Patient();
        patient.setName(patientRequestDTO.getName());
        patient.setAddress(patientRequestDTO.getAddress());
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));
        patient.setRegisteredDate(LocalDate.parse(patientRequestDTO.getRegisteredDate()));
        return patient;

    }
}
