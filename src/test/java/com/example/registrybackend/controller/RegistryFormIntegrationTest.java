package com.example.registrybackend.controller;

import com.example.registrybackend.domain.RegistryForm;
import com.example.registrybackend.service.RegistryFormService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class RegistryFormIntegrationTest {

    @Autowired
    private RegistryFormService registryFormService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testSaveAndRetrieveRegistryForm() {
        RegistryForm originalRegistryForm = new RegistryForm();
        originalRegistryForm.setSurname("TestSurname");
        originalRegistryForm.setName("TestName");
        originalRegistryForm.setPatronymic("TestPatronymic");
        originalRegistryForm.setPolicySeries("TestPolicySeries");
        originalRegistryForm.setPolicyNumber("TestPolicyNumber");
        originalRegistryForm.setBirthDate(LocalDate.now());
        originalRegistryForm.setPhone("TestPhone");
        originalRegistryForm.setEmail("TestEmail");
        originalRegistryForm.setDoctorFullName("TestDoctorFullName");

        // Save the original RegistryForm
        RegistryForm savedRegistryForm = registryFormService.saveRegistryForm(originalRegistryForm);

        // Retrieve the saved RegistryForm
        RegistryForm retrievedRegistryForm = registryFormService.getRegistryFormById(savedRegistryForm.getId()).orElse(null);

        // Assert that the retrieved RegistryForm is not null and its data matches the original data
        assertNotNull(retrievedRegistryForm);
        assertEquals(originalRegistryForm.getSurname(), retrievedRegistryForm.getSurname());
        assertEquals(originalRegistryForm.getName(), retrievedRegistryForm.getName());
        assertEquals(originalRegistryForm.getPatronymic(), retrievedRegistryForm.getPatronymic());
        assertEquals(originalRegistryForm.getPolicySeries(), retrievedRegistryForm.getPolicySeries());
        assertEquals(originalRegistryForm.getPolicyNumber(), retrievedRegistryForm.getPolicyNumber());
        assertEquals(originalRegistryForm.getBirthDate(), retrievedRegistryForm.getBirthDate());
        assertEquals(originalRegistryForm.getPhone(), retrievedRegistryForm.getPhone());
        assertEquals(originalRegistryForm.getEmail(), retrievedRegistryForm.getEmail());
        assertEquals(originalRegistryForm.getDoctorFullName(), retrievedRegistryForm.getDoctorFullName());
    }

    @Test
    public void testDeleteRegistryForm() {
        RegistryForm originalRegistryForm = new RegistryForm();
        originalRegistryForm.setSurname("TestSurname");
        originalRegistryForm.setName("TestName");
        originalRegistryForm.setPatronymic("TestPatronymic");
        originalRegistryForm.setPolicySeries("TestPolicySeries");
        originalRegistryForm.setPolicyNumber("TestPolicyNumber");
        originalRegistryForm.setBirthDate(LocalDate.now());
        originalRegistryForm.setPhone("TestPhone");
        originalRegistryForm.setEmail("TestEmail");
        originalRegistryForm.setDoctorFullName("TestDoctorFullName");

        // Save the original RegistryForm
        RegistryForm savedRegistryForm = registryFormService.saveRegistryForm(originalRegistryForm);

        // Delete the saved RegistryForm
        registryFormService.deleteRegistryForm(savedRegistryForm.getId());

        // Assert that the deleted RegistryForm cannot be retrieved
        assertFalse(registryFormService.getRegistryFormById(savedRegistryForm.getId()).isPresent());
    }

    @Test
    public void testGetAllRegistryForms() {
        RegistryForm registryForm1 = new RegistryForm();
        registryForm1.setSurname("TestSurname1");
        registryForm1.setName("TestName1");
        registryForm1.setPatronymic("TestPatronymic1");
        registryForm1.setPolicySeries("TestPolicySeries1");
        registryForm1.setPolicyNumber("TestPolicyNumber1");
        registryForm1.setBirthDate(LocalDate.now());
        registryForm1.setPhone("TestPhone1");
        registryForm1.setEmail("TestEmail1");
        registryForm1.setDoctorFullName("TestDoctorFullName1");

        RegistryForm registryForm2 = new RegistryForm();
        registryForm2.setSurname("TestSurname2");
        registryForm2.setName("TestName2");
        registryForm2.setPatronymic("TestPatronymic2");
        registryForm2.setPolicySeries("TestPolicySeries2");
        registryForm2.setPolicyNumber("TestPolicyNumber2");
        registryForm2.setBirthDate(LocalDate.now());
        registryForm2.setPhone("TestPhone2");
        registryForm2.setEmail("TestEmail2");
        registryForm2.setDoctorFullName("TestDoctorFullName2");

        // Save the RegistryForms
        registryFormService.saveRegistryForm(registryForm1);
        registryFormService.saveRegistryForm(registryForm2);

        // Retrieve all RegistryForms
        List<RegistryForm> allRegistryForms = registryFormService.getAllRegistryForms();

        // Assert that the retrieved RegistryForms include the saved RegistryForms
        assertTrue(allRegistryForms.stream().anyMatch(rf -> rf.getSurname().equals("TestSurname1")));
        assertTrue(allRegistryForms.stream().anyMatch(rf -> rf.getSurname().equals("TestSurname2")));
    }

    @Test
    public void testCreateRegistryForm() throws Exception {
        mockMvc.perform(post("/api/registryForms")
                        .contentType("application/json")
                        .content("{\"surname\":\"TestSurname\",\"name\":\"TestName\",\"patronymic\":\"TestPatronymic\",\"policySeries\":\"TestPolicySeries\",\"policyNumber\":\"TestPolicyNumber\",\"birthDate\":\"2022-01-01\",\"phone\":\"TestPhone\",\"email\":\"TestEmail\",\"doctorFullName\":\"TestDoctorFullName\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.surname", is("TestSurname")));
    }

    @Test
    public void testUpdateRegistryForm() throws Exception {
        RegistryForm originalRegistryForm = new RegistryForm();
        originalRegistryForm.setSurname("TestSurname");
        originalRegistryForm.setName("TestName");
        originalRegistryForm.setPatronymic("TestPatronymic");
        originalRegistryForm.setPolicySeries("TestPolicySeries");
        originalRegistryForm.setPolicyNumber("TestPolicyNumber");
        originalRegistryForm.setBirthDate(LocalDate.now());
        originalRegistryForm.setPhone("TestPhone");
        originalRegistryForm.setEmail("TestEmail");
        originalRegistryForm.setDoctorFullName("TestDoctorFullName");

        // Save the original RegistryForm
        RegistryForm savedRegistryForm = registryFormService.saveRegistryForm(originalRegistryForm);

        mockMvc.perform(put("/api/registryForms/" + savedRegistryForm.getId())
                        .contentType("application/json")
                        .content("{\"surname\":\"UpdatedSurname\",\"name\":\"TestName\",\"patronymic\":\"TestPatronymic\",\"policySeries\":\"TestPolicySeries\",\"policyNumber\":\"TestPolicyNumber\",\"birthDate\":\"2022-01-01\",\"phone\":\"TestPhone\",\"email\":\"TestEmail\",\"doctorFullName\":\"TestDoctorFullName\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.surname", is("UpdatedSurname")));
    }
}
