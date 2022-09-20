package com.patient.health.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "PATIENT")
public class Patient {
	
	        
	        @Id
	        @GeneratedValue(strategy = GenerationType.IDENTITY)
	        @Column(name="PATIENT_ID")
	        private Integer patientId;
	        
	        @NotBlank(message = "Name is mandatory")
	        @Column(name="NAME")
	        private String name;
	        
	        @NotBlank(message = "Date of Birth  is mandatory")
	        @Column(name="DATE_BIRTH")
	        private String dob;
	        
	        @NotBlank(message = "Gender is mandatory")
	        @Column(name="GENDER")
	        private String gender;
	        
	        @NotBlank(message = "Address is mandatory")
	        @Column(name="ADDRESS")
	        private String address;
	        
	        @NotBlank(message = "Telephone no is mandatory")
	        @Column(name="TELEPHONE_NUMBER")
	        private String telephonenumber;

			public Integer getPatientId() {
				return patientId;
			}

			public void setPatientId(Integer patientId) {
				this.patientId = patientId;
			}

			public String getName() {
				return name;
			}

			public void setName(String name) {
				this.name = name;
			}

			public String getDob() {
				return dob;
			}

			public void setDob(String dob) {
				this.dob = dob;
			}

			public String getGender() {
				return gender;
			}

			public void setGender(String gender) {
				this.gender = gender;
			}

			public String getAddress() {
				return address;
			}

			public void setAddress(String address) {
				this.address = address;
			}

			public String getTelephonenumber() {
				return telephonenumber;
			}

			public void setTelephonenumber(String telephonenumber) {
				this.telephonenumber = telephonenumber;
			}

			@Override
			public String toString() {
				return "Patient [patientId=" + patientId + ", name=" + name + ", dob=" + dob + ", gender=" + gender
						+ ", address=" + address + ", telephonenumber=" + telephonenumber + "]";
			}

			
	        
	}