package com.patient.health.controller;

import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.patient.health.repo.PatientRepository;
import com.patient.health.service.PatientService;

@WebMvcTest
public class PatientControllerTest {
	@SuppressWarnings("unused")
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PatientRepository patientRepo;


	@MockBean
	private PatientService patientService;

	@InjectMocks
	private PatientController patientController;
	
	
	
}