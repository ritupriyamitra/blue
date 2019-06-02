package com.lazybuds;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lazybuds.common.Gender;
import com.lazybuds.common.Language;

@Controller
public class ValuesController {

	@RequestMapping(value = "/values/genders", method = RequestMethod.GET)
	public @ResponseBody Gender[] getGenders() {
		return Gender.values();
	}

	@RequestMapping(value = "/values/languages", method = RequestMethod.GET)
	public @ResponseBody Language[] getLanguages() {
		return Language.values();
	}

}
