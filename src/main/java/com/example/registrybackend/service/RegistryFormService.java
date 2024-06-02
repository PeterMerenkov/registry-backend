package com.example.registrybackend.service;

import com.example.registrybackend.domain.RegistryForm;
import com.example.registrybackend.repository.RegistryFormRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class RegistryFormService {

    private final RegistryFormRepository registryFormRepository;

    public List<RegistryForm> getAllRegistryForms() {
        return registryFormRepository.findAll();
    }

    public Optional<RegistryForm> getRegistryFormById(Long id) {
        return registryFormRepository.findById(id);
    }

    public RegistryForm saveRegistryForm(RegistryForm registryForm) {
        return registryFormRepository.save(registryForm);
    }

    public void deleteRegistryForm(Long id) {
        registryFormRepository.deleteById(id);
    }
}
