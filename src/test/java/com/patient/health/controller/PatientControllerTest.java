package com.patient.health.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import com.patient.health.exception.ResourceNotFoundException;
import com.patient.health.model.Patient;
import com.patient.health.repo.PatientRepository;
import com.patient.health.request.PatientRequest;
import com.patient.health.service.PatientService;

@WebMvcTest
public class PatientControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PatientRepository patientRepository;


	@MockBean
	private PatientService patientService;

	@InjectMocks
	private PatientController patientController;
	
	private String validResponseBody = "{" + "\"name\":\"Mukesh\"," 
			+ "\"gender\":\"Male\"," + "\"dateOfBirth\":\"10-10-1989\"," + "\"address\":\"Delhi\","
			+ "\"phoneNumber\":\"9910158813\"}";
	
	/*
	 * private String invalidResponseBody = "{" + "\"name\":\"@\"," +
	 * "\"gender\":\"Male\"," + "\"dateOfBirth\":\"10-10-1989\"," +
	 * "\"address\":\"Delhi\"," + "\"phoneNumber\":\"9910158813\"}";
	 */
	
	
	private List<Patient> getpatient() {
		List<Patient> patients = new ArrayList<Patient>();

		Patient patient1 = new Patient();
		patient1.setPatientId(1233);
		patient1.setName("Mukesh");
		patient1.setGender("Male");
		patient1.setDob("10/10/1989");
		patient1.setAddress("Delhi");
		patient1.setTelephonenumber("9910158813");
		
		patients.add(patient1);

		return patients;
	}
    
	private Patient getPatientsType() {
		Patient patient1 = new Patient();
		patient1.setPatientId(1233);
		patient1.setName("Mukesh");
		patient1.setGender("Male");
		patient1.setDob("10/10/1989");
		patient1.setAddress("Delhi");
		patient1.setTelephonenumber("9910158813");
		return patient1;
	}
	@Test
	public void getPatients() throws Exception {
		
		Mockito.when(patientRepository.findAll()).thenReturn(getpatient());
		Mockito.when(patientService.getAllPatient()).thenReturn(getpatient());

		MvcResult requestResult = this.mockMvc.perform(get("/api/patient")).andExpect(status().isOk())
				.andExpect(status().isOk()).andReturn();
		String result = requestResult.getResponse().getContentAsString();
		assertNotNull(result);
	}
	
	@Test
	public void getPatientsEmpty() throws Exception {
		Mockito.when(patientRepository.findAll()).thenReturn(new ArrayList<Patient>());
		Mockito.when(patientService.getAllPatient()).thenReturn(new ArrayList<Patient>());

		MvcResult requestResult = this.mockMvc.perform(get("/api/patient")).andExpect(status().isOk())
				.andExpect(status().isOk()).andReturn();
		String result = requestResult.getResponse().getContentAsString();
		assertNotNull(result);
	}
	
	
	@Test
	public void getPatientById() throws Exception {
		Mockito.when(patientRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(getpatient().get(0)));
		Mockito.when(patientService.getASinglePatient(Mockito.anyInt())).thenReturn(getpatient().get(0));

		MvcResult requestResult = this.mockMvc.perform(get("/api/patient/0")).andExpect(status().isOk())
				.andExpect(status().isOk()).andReturn();
		String result = requestResult.getResponse().getContentAsString();
		assertNotNull(result);
	}

	
	@Test
	public void getPatientByIdNotFound() throws Exception {
		Mockito.when(patientService.getASinglePatient(Mockito.anyInt())).thenThrow(ResourceNotFoundException.class);

		MvcResult requestResult = this.mockMvc.perform(get("/api/patient/0")).andExpect(status().isNotFound())
				.andExpect(status().isNotFound()).andReturn();
		String result = requestResult.getResponse().getContentAsString();
		assertNotNull(result);
	}
	
	@Test
	public void addPatient() throws Exception {
		Mockito.when(patientRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(getpatient().get(0)));
		Mockito.when(patientService.getAllPatient()).thenReturn(getpatient());

		MvcResult requestResult = mockMvc
				.perform(post("/api/patient")
						.header("Content-Type", MediaType.APPLICATION_JSON)
						.content(validResponseBody))
				.andExpect(status().isCreated()).andExpect(status().isCreated()).andReturn();

		String result = requestResult.getResponse().getContentAsString();
		assertNotNull(result);
	}
	
	@Test
	public void updatePatient() throws Exception {
		Mockito.when(patientRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(getpatient().get(0)));
		Mockito.when(patientService.updatePatient(Mockito.anyInt(), Mockito.any(PatientRequest.class)))
				.thenReturn(Optional.of(getPatientsType()));
		MvcResult requestResult = mockMvc.perform(
				put("/api/patient/17")
				.header("Content-Type", MediaType.APPLICATION_JSON).content(validResponseBody))
				.andExpect(status().isOk()).andExpect(status().isOk()).andReturn();

		String result = requestResult.getResponse().getContentAsString();
		assertNotNull(result);
	}
	
	@Test
	public void deletePatient() throws Exception {
		Mockito.when(patientRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(getPatientsType()));

		MvcResult requestResult = this.mockMvc.perform(delete("/api/patient/0")).andExpect(status().isOk())
				.andExpect(status().isOk()).andReturn();
		String result = requestResult.getResponse().getContentAsString();
		assertNotNull(result);
	}

}