package com.pm.patientservice.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pm.patientservice.models.Patient;

@Repository
public interface PatientRepo extends JpaRepository<Patient, UUID> {
    boolean existsByEmail(String email);
}
