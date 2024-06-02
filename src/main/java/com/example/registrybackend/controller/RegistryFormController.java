package com.example.registrybackend.controller;

import com.example.registrybackend.domain.RegistryForm;
import com.example.registrybackend.service.RegistryFormService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/registryForms")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class RegistryFormController {

    private final RegistryFormService registryFormService;

    @GetMapping
    public ResponseEntity<List<RegistryForm>> getAllRegistryForms() {
        List<RegistryForm> registryForms = registryFormService.getAllRegistryForms();
        return new ResponseEntity<>(registryForms, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistryForm> getRegistryFormById(@PathVariable Long id) {
        return registryFormService.getRegistryFormById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<RegistryForm> createRegistryForm(@RequestBody RegistryForm registryForm) {
        RegistryForm savedRegistryForm = registryFormService.saveRegistryForm(registryForm);
        return new ResponseEntity<>(savedRegistryForm, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RegistryForm> updateRegistryForm(@PathVariable Long id, @RequestBody RegistryForm registryForm) {
        return registryFormService.getRegistryFormById(id)
                .map(existingRegistryForm -> {
                    registryForm.setId(id);
                    return ResponseEntity.ok(registryFormService.saveRegistryForm(registryForm));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRegistryForm(@PathVariable Long id) {
        if (registryFormService.getRegistryFormById(id).isPresent()) {
            registryFormService.deleteRegistryForm(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
