package com.patient.health.controller;

import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.patient.health.model.Patient;
import com.patient.health.repo.PatientRepository;
import com.patient.health.request.PatientRequest;
import com.patient.health.response.MessageResponse;
import com.patient.health.service.PatientService;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")

@ApiResponses(value = {
		@io.swagger.annotations.ApiResponse(code = 400, message = "This is a bad request, please follow the API documentation for the proper request format"),
		@io.swagger.annotations.ApiResponse(code = 401, message = "Due to security constraints, your access request cannot be authorized"),
		@io.swagger.annotations.ApiResponse(code = 500, message = "The server is down. Please bear with us."), })
public class PatientController {

	@Autowired
	PatientService patientService;
	@Autowired
	PatientRepository patientRepository;

	/**
	 * Logger object.
	 */
	private static Logger logger = LogManager.getLogger();

	/**
	 * To get of a patient details
	 * 
	 * @author Mukesh Yadav
	 * @RequestBody JosnObject of the details of the patient
	 * @return get of patient details
	 */

	@GetMapping("/patient")
	public ResponseEntity<List<Patient>> getAllPatient() {
		List<Patient> patient = patientService.getAllPatient();
		return new ResponseEntity<>(patient, HttpStatus.OK);
	}

	/**
	 * To List of a patient details
	 * 
	 * @author Mukesh Yadav
	 * @RequestBody JosnObject of the details of the patient
	 * @return list of patient details
	 */

	@GetMapping("/patient/{patientId}")
	public ResponseEntity<Patient> getPatientById(@PathVariable Integer patientId) {
		logger.info("PatientController...getPatient()...patientId = [{}]", patientId);
		Patient patient = patientService.getASinglePatient(patientId);
		return new ResponseEntity<>(patient, HttpStatus.OK);
	}

	/**
	 * To create a patient details
	 * 
	 * @author Mukesh Yadav
	 * @RequestBody JosnObject of the details of the patient
	 * @return Saved patient details
	 */
	@PostMapping("/patient")
	public ResponseEntity<MessageResponse> addPatient(@Valid @RequestBody PatientRequest patient) {
		logger.info("PatientController...addPatient details...patient = [{}]", patient);
		MessageResponse newPatient = patientService.createPatient(patient);
		return new ResponseEntity<>(newPatient, HttpStatus.CREATED);
	}

	/**
	 * To Update a patient details
	 * 
	 * @author Mukesh Yadav
	 *@param patientId     {@link Long}
	 * @param updatePatient {@link Patient}
	 * @return {@link ResponseEntity} of {@link Object}
	 */

	@PutMapping("/patient/{patientId}")
	public Optional<Patient> updatePatient(@PathVariable Integer patientId,
			@Valid @RequestBody PatientRequest patientRequest) {
		logger.info("PatientController...updatePatient details...patientId = [{}]...patient = [{}]", patientId);
		return patientService.updatePatient(patientId, patientRequest);

	}

	/**
	 * To Delete a patient details
	 * 
	 * @author Mukesh Yadav
	 * @RequestBody JosnObject of the details of the patient
	 * @return {@link ResponseEntity} of {@link Object}
	 */

	@DeleteMapping("/patient/{patientId}")
	public ResponseEntity<?> deletePatient(@PathVariable("patientId") Integer patientId) {
		logger.info("PatientController...deletePatient details...patientId = [{}]", patientId);
		patientService.deletePatient(patientId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/patient/name")
	public ResponseEntity<List<Patient>> getPatientByName(@PathVariable String name) {
		return new ResponseEntity<List<Patient>>(patientService.findByName(name), HttpStatus.OK);

	}

}