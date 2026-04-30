package com.pm.patient_service.service;

import com.pm.patient_service.dto.PatientRequestDTO;
import com.pm.patient_service.dto.PatientResponceDTO;
import com.pm.patient_service.exception.EmailAlreadyExistsExeption;
import com.pm.patient_service.exception.PatientNotFoundExeption;
import com.pm.patient_service.mapper.PatientMapper;
import com.pm.patient_service.model.Patient;
import com.pm.patient_service.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class PatientService {
    private PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<PatientResponceDTO> getPatients(){
        List<Patient> patients = patientRepository.findAll();
        return patients.stream().map(PatientMapper::toDTO).toList();
    }

    public PatientResponceDTO cereatePatient (PatientRequestDTO patientRequestDTO){
        if (patientRepository.existsByEmail(patientRequestDTO.getEmail())){
            throw new EmailAlreadyExistsExeption("a patient with this email"+"already exists :"+patientRequestDTO.getEmail());
        }

        Patient newPatient = patientRepository.save(PatientMapper.toModel(patientRequestDTO));
        return PatientMapper.toDTO(newPatient);
    }

    public PatientResponceDTO updatePatient (UUID id, PatientRequestDTO patientRequestDTO){

        Patient patient = patientRepository.findById(id).orElseThrow(()-> new PatientNotFoundExeption("Patient not found with Id: "+ id));

        if (patientRepository.existsByEmail(patientRequestDTO.getEmail())){
            throw new EmailAlreadyExistsExeption("a patient with this email "+"already exists :"+patientRequestDTO.getEmail());
        }

        patient.setName(patientRequestDTO.getName());
        patient.setAddress(patientRequestDTO.getAddress());
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));
        Patient updatedPatient = patientRepository.save(patient);
        return PatientMapper.toDTO(updatedPatient);
    }
}
