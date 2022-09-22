package com.patient.health.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;
    @Getter
	private String message;
    
	public ResourceNotFoundException(String message) {
		//super();
		this.message = message;
	}
   
    
}
