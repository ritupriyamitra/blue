package com.lazybuds.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class ValidationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ValidationException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}


}
