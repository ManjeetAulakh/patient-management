package com.pm.patientservice.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.pm.patientservice.dtos.PatientRequestDto;
import com.pm.patientservice.dtos.PatientResponseDto;
import com.pm.patientservice.exceptions.EmailAlreadyExistsException;
import com.pm.patientservice.exceptions.PatientNotFoundException;
import com.pm.patientservice.mappers.PatientMapper;
import com.pm.patientservice.models.Patient;
import com.pm.patientservice.repository.PatientRepo;

@Service
public class PatientService {

    private PatientRepo patientRepo;

    public PatientService(PatientRepo patientRepo){
        this.patientRepo = patientRepo;
    }

    public List<PatientResponseDto> getAllPatients(){

        List<Patient> patients = patientRepo.findAll(); 
        // List<PatientResponseDto> patientResponseDtos = patients.stream()
        //         .map(patient -> PatientMapper.toDto(patient)).toList();
        // by lamda exp

         List<PatientResponseDto> patientResponseDtos = patients.stream()
                .map(PatientMapper::toDto).toList();

        return patientResponseDtos;
    }

    public PatientResponseDto createPateint(PatientRequestDto patientRequestDto){

        if(patientRepo.existsByEmail(patientRequestDto.getEmail())){
            throw new EmailAlreadyExistsException("A patient with this email already exists: " 
                + patientRequestDto.getEmail());
        }

        Patient newpatient = patientRepo.save(PatientMapper.toPatient(patientRequestDto));
        return PatientMapper.toDto(newpatient);
    }

    public PatientResponseDto updatePateint(UUID id ,PatientRequestDto patientRequestDto){

        Patient patient = patientRepo.findById(id).orElseThrow(
            () -> new PatientNotFoundException("Patient not found with this Id: " + id)
        );

        if(patientRepo.existsByEmail(patientRequestDto.getEmail())){
            throw new EmailAlreadyExistsException("A patient with this email already exists: " 
                + patientRequestDto.getEmail());
        }

        patient.setName(patientRequestDto.getName());
        patient.setEmail(patientRequestDto.getEmail());
        patient.setAddress(patientRequestDto.getAddress());
        patient.setDateOfBirth(patientRequestDto.getDateOfBirth());

        Patient updatedPatient = patientRepo.save(patient);

        return PatientMapper.toDto(updatedPatient);
    }

    public void deletePatient(UUID id){
    
        patientRepo.findById(id).orElseThrow(
            () -> new PatientNotFoundException("Patient not found with this Id: " + id)
        );
        
        patientRepo.deleteById(id);
    }

}
