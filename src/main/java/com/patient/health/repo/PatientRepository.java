package com.patient.health.repo;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.patient.health.model.Patient;



@Repository
public interface PatientRepository extends JpaRepository<Patient,Integer> {

	List<Patient> findByName(String name);
}
