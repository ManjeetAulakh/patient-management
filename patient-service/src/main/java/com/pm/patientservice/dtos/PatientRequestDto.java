package com.pm.patientservice.dtos;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PatientRequestDto {

    @NotBlank
    @Size(max = 100, message = "Name cannot be more then 100 charters")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Enter valid email")
    private String email;

    @NotBlank(message = "Address is required")
    private String address;

    @NotNull
    private LocalDate dateOfBirth;

    private LocalDate registeredDate;


    public PatientRequestDto() {
    }

    public PatientRequestDto(@NotBlank @Size(max = 100, message = "Name cannot be more then 100 charters") String name,
            @NotBlank(message = "Email is required") @Email(message = "Enter valid email") String email,
            @NotBlank(message = "Address is required") String address,
            @NotNull LocalDate dateOfBirth,
            @NotNull LocalDate registeredDate) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.registeredDate = registeredDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDate getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(LocalDate registeredDate) {
        this.registeredDate = registeredDate;
    }

    
}
