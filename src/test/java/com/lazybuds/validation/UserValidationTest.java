package com.lazybuds.validation;

import static org.junit.Assert.fail;

import org.junit.Test;

import com.lazybuds.common.Gender;
import com.lazybuds.common.Language;
import com.lazybuds.exceptions.ValidationException;
import com.lazybuds.model.User;

public class UserValidationTest {

	@Test
	public void validateUserObject() {

		User user = new User();
		user.setDeviceId("1234");
		user.setLocation("Seattle");
		user.setLanguage(Language.ENGLISH);
		user.setGender(Gender.MALE);
		try {
			UserValidation.validate(user);
		} catch (Exception e) {
			fail("Should not be throwing exception");
		}
	}
	
	@Test(expected = ValidationException.class)
	public void validateUserObjectWithoutDeviceId() {

		User user = new User();
		user.setDeviceId("");
		user.setLocation("Seattle");
		user.setLanguage(Language.ENGLISH);
		user.setGender(Gender.MALE);
		UserValidation.validate(user);
		
	}

}
