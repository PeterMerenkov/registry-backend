package com.example.registrybackend.controller;

import com.example.registrybackend.domain.RegistryForm;
import com.example.registrybackend.service.RegistryFormService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class RegistryFormControllerTest {

    @Mock
    private RegistryFormService registryFormService;

    @InjectMocks
    private RegistryFormController registryFormController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(registryFormController).build();
    }

    @Test
    public void testGetAllRegistryForms() throws Exception {
        when(registryFormService.getAllRegistryForms()).thenReturn(Arrays.asList(new RegistryForm(), new RegistryForm()));
        mockMvc.perform(get("/api/registryForms")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetRegistryFormById() throws Exception {
        when(registryFormService.getRegistryFormById(anyLong())).thenReturn(Optional.of(new RegistryForm()));
        mockMvc.perform(get("/api/registryForms/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateRegistryForm() throws Exception {
        when(registryFormService.saveRegistryForm(any(RegistryForm.class))).thenReturn(new RegistryForm());
        mockMvc.perform(post("/api/registryForms")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"surname\":\"Test\",\"name\":\"Test\",\"patronymic\":\"Test\",\"policySeries\":\"Test\",\"policyNumber\":\"Test\",\"birthDate\":\"2022-01-01\",\"phone\":\"Test\",\"email\":\"Test\",\"doctorFullName\":\"Test\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    public void testUpdateRegistryForm() throws Exception {
        when(registryFormService.getRegistryFormById(anyLong())).thenReturn(Optional.of(new RegistryForm()));
        when(registryFormService.saveRegistryForm(any(RegistryForm.class))).thenReturn(new RegistryForm());
        mockMvc.perform(put("/api/registryForms/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"surname\":\"Test\",\"name\":\"Test\",\"patronymic\":\"Test\",\"policySeries\":\"Test\",\"policyNumber\":\"Test\",\"birthDate\":\"2022-01-01\",\"phone\":\"Test\",\"email\":\"Test\",\"doctorFullName\":\"Test\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteRegistryForm() throws Exception {
        when(registryFormService.getRegistryFormById(anyLong())).thenReturn(Optional.of(new RegistryForm()));
        mockMvc.perform(delete("/api/registryForms/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}