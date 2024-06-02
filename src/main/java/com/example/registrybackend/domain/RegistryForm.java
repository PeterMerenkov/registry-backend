package com.example.registrybackend.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class RegistryForm {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String surname;
    private String name;
    private String patronymic;
    private String policySeries;
    private String policyNumber;
    private LocalDate birthDate;
    private String phone;
    private String email;
    private String doctorFullName;
}
