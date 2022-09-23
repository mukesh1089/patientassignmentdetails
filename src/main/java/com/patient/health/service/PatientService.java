package com.patient.health.service;

import com.patient.health.model.Patient;
import com.patient.health.request.PatientRequest;
import com.patient.health.response.MessageResponse;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface PatientService {
	
	MessageResponse createPatient(PatientRequest patientRequest);

	Optional<Patient> updatePatient(Integer patientId, PatientRequest patientRequest);

	void deletePatient(Integer patientId);

	Patient getASinglePatient(Integer patientId);

	List<Patient> getAllPatient();

	List<Patient> findByName(String name);

}
