package com.lazybuds;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lazybuds.model.User;

@Controller
public class UserController {

	@RequestMapping("/user/{id}")
	public @ResponseBody User getUser(@PathVariable String id) {
		return mockUser();
	}

	private User mockUser() {
		User user = new User();
		user.setUserId("1234");
		user.setFirstName("Jon");
		user.setLastName("Snow");
		
		return user;
	}
}
