package com.patient.health.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PatientRequest {

	@NotBlank(message = "Name is mandatory")
	@NotNull(message = "Name is not null")
	private String name;
	
	@NotBlank(message = "Gender is mandatory")
	@NotNull(message = "Name is not null")
	private String gender;
	
	@NotBlank(message = "Telephone no is mandatory")
	@NotNull(message = "Telephone is not null")
	private String telephonenumber;
	
	@NotBlank(message = "Address is mandatory")
	@NotNull(message = "Address is not null")
	private String address;
	
	@NotBlank(message = "Date of Birth  is mandatory")
	@NotNull(message = "Date of Birth is not null")
	private String dob;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getTelephonenumber() {
		return telephonenumber;
	}

	public void setTelephonenumber(String telephonenumber) {
		this.telephonenumber = telephonenumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

}
