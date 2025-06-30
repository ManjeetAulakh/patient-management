package com.pm.patientservice.mappers;

import com.pm.patientservice.dtos.PatientRequestDto;
import com.pm.patientservice.dtos.PatientResponseDto;
import com.pm.patientservice.models.Patient;

public class PatientMapper {
    public static PatientResponseDto toDto(Patient patient){
        PatientResponseDto pDto = new PatientResponseDto();

        pDto.setId(patient.getId());
        pDto.setName(patient.getName());
        pDto.setEmail(patient.getEmail());
        pDto.setAddress(patient.getAddress());
        pDto.setDateOfBirth(patient.getDateOfBirth());
        pDto.setRegisteredDate(patient.getRegistereDate());

        return pDto;
    }

    public static Patient toPatient(PatientRequestDto pDto){
        Patient patient = new Patient();

        patient.setName(pDto.getName());
        patient.setEmail(pDto.getEmail());
        patient.setAddress(pDto.getAddress());
        patient.setDateOfBirth(pDto.getDateOfBirth());
        patient.setRegistereDate(pDto.getRegisteredDate());

        return patient;
    }
}
