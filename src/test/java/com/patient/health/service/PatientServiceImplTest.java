package com.patient.health.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.patient.health.exception.ResourceNotFoundException;
import com.patient.health.model.Patient;
import com.patient.health.repo.PatientRepository;
import com.patient.health.request.PatientRequest;


@ExtendWith(MockitoExtension.class)
public class PatientServiceImplTest
{
	@Mock
	private PatientRepository patientRepository;

	
	
	@InjectMocks
	private PatientServiceImpl patientService;

    
  
    
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
    
    @Test
    public void getPatients() {
		List<Patient> patients = new ArrayList<>();
		patients.add(getpatient().get(0));

		Mockito.when(patientRepository.findAll()).thenReturn(patients);
		assertThat(!patientService.getAllPatient().isEmpty());
	}
    @Test
	public void getPatientByIdNotFound() {
    	RuntimeException exception = Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			patientService.getASinglePatient(Mockito.anyInt());
		});
		System.out.println(exception.getMessage());
		assertThat(exception.getMessage());
	}

	@Test
	public void addPatient() {
		Mockito.when(patientRepository.save(Mockito.any(Patient.class))).thenReturn(getpatient().get(0));
		PatientRequest patientob=new PatientRequest();
		patientob.setName("Mukesh");
		patientob.setGender("Male");
		patientob.setDob("10/10/90");
		patientob.setAddress("Delhi");
		patientob.setTelephonenumber("019191001");
		assertNotNull(patientService.createPatient(patientob));
	}
}

