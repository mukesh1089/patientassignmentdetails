package com.patient.health.service;

import com.patient.health.exception.ResourceNotFoundException;
import com.patient.health.model.Patient;
import com.patient.health.repo.PatientRepository;
import com.patient.health.request.PatientRequest;
import com.patient.health.response.MessageResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    PatientRepository patientRepository;

    @Override
    public MessageResponse createPatient(PatientRequest patientRequest) {
        Patient newPatient = new Patient();
        newPatient.setName(patientRequest.getName());
        newPatient.setGender(patientRequest.getGender());
        newPatient.setAddress(patientRequest.getAddress());
        newPatient.setDob(patientRequest.getDob());
        newPatient.setTelephonenumber(patientRequest.getTelephonenumber());
        
        patientRepository.save(newPatient);
        return new MessageResponse("New Patient details created successfully");

    }

    @Override
    public Optional<Patient> updatePatient(Integer patientId, PatientRequest patientRequest)  throws ResourceNotFoundException{
        Optional<Patient> patient = patientRepository.findById(patientId);
        if (patient.isEmpty()){
        throw new ResourceNotFoundException("Patient", "id", patientId);
        }
        else
        patient.get().setName(patientRequest.getName());
        patient.get().setGender(patientRequest.getGender());
        patient.get().setAddress(patientRequest.getAddress());
        patient.get().setTelephonenumber(patientRequest.getTelephonenumber());
        patient.get().setDob(patientRequest.getDob());
        patientRepository.save(patient.get());
        return patient;
    }

    @Override
    public Patient getASinglePatient(Integer patientId) throws ResourceNotFoundException{
        return patientRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient", "patientId", patientId));
    }

    @Override
    public List<Patient> getAllPatient() {
        return patientRepository.findAll();
    }
    @Override
    public void deletePatient(Integer patientId) throws ResourceNotFoundException {
        if (patientRepository.getById(patientId).getPatientId().equals(patientId)){
        	patientRepository.deleteById(patientId);
        }
        else throw new ResourceNotFoundException("Patient", "patientId", patientId);
    }

	@Override
	public List<Patient> findByName(String name) {
		return patientRepository.findByName(name);
	}
}
