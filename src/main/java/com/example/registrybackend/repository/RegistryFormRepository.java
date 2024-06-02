package com.example.registrybackend.repository;

import com.example.registrybackend.domain.RegistryForm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistryFormRepository extends JpaRepository<RegistryForm, Long> {
}
