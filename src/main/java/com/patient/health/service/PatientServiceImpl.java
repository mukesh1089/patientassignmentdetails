package com.patient.health.service;

import com.patient.health.exception.ResourceNotFoundException;
import com.patient.health.model.Patient;
import com.patient.health.repo.PatientRepository;
import com.patient.health.request.PatientRequest;
import com.patient.health.response.MessageResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    PatientRepository patientRepository;
    /**
	 * Logger object.
	 */
	private static Logger logger = LogManager.getLogger();

    @Override
    public MessageResponse createPatient(PatientRequest patientRequest) {
    	
    	logger.info("PatientService...addPatient details...patient = [{}]", patientRequest);
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
    	logger.info("PatientService...updatePatient details...patientId = [{}]...patient = [{}]", patientId, patientRequest);
    	Optional<Patient> patient = patientRepository.findById(patientId);
        if (patient.isEmpty()){
        throw new ResourceNotFoundException("Patient not found with Id = " + patientId);
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
    	logger.info("PatientService...getPatient()...patientId = [{}]", patientId);
        return patientRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with Id = " + patientId));
    }

    @Override
    public List<Patient> getAllPatient() {
        return patientRepository.findAll();
    }
    @Override
    public void deletePatient(Integer patientId) throws ResourceNotFoundException {
    	
    	logger.info("PatientService...deletePatient details...patientId = [{}]", patientId);
    	Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with Id = " + patientId));
    	patientRepository.delete(patient);
    }
        
	@Override
	public List<Patient> findByName(String name) {
		return patientRepository.findByName(name);
	}
}
