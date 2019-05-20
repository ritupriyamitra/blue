package com.lazybuds.validation;

import com.lazybuds.common.StringUtil;
import com.lazybuds.exceptions.ValidationException;
import com.lazybuds.model.User;

public class UserValidation {

	public static void validate(User user) {
		if (StringUtil.isNullOrEmpty(user.getDeviceId())) {
			throw new ValidationException("Device Id Required");
		}

		if (user.getIntro() != null) {
			throw new ValidationException("Intro value should not be passed");
		}

		if (StringUtil.isNullOrEmpty(user.getLocation())) {
			throw new ValidationException("Location Required");
		}

		if (user.getLanguage() == null) {
			throw new ValidationException("Language Required");
		}

		if (user.getGender() == null) {
			throw new ValidationException("Gender Required");
		}

	}

}
