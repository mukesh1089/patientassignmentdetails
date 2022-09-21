package com.patient.health.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;
    private String message;
	public ResourceNotFoundException(String message) {
		super();
		this.message = message;
	}
   
    
}
