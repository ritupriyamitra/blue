package com.lazybuds;

import java.util.Locale;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.lazybuds.exceptions.ValidationException;
import com.lazybuds.model.ErrorMessage;

public class AbstractController {
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = ValidationException.class)
	public @ResponseBody ErrorMessage handleError(Exception ex, Locale locale) {
		ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setErrorMessage(ex.getMessage());
		
		return errorMessage;
	}

}
