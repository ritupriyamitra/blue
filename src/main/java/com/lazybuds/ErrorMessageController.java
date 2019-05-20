package com.lazybuds;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lazybuds.model.ErrorMessage;

@Controller
public class ErrorMessageController {
 
	@ExceptionHandler(value = Exception.class)
	public @ResponseBody ErrorMessage handleError(Exception ex, Locale locale) {
		ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setErrorMessage(ex.getMessage());
		
		return errorMessage;
	}
	
}
