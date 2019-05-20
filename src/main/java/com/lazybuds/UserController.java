package com.lazybuds;

import java.util.Locale;

import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lazybuds.dao.UserDao;
import com.lazybuds.exceptions.ValidationException;
import com.lazybuds.model.ErrorMessage;
import com.lazybuds.model.User;
import com.lazybuds.validation.UserValidation;

@Controller
@Transactional
public class UserController extends AbstractController {
	
	private static Log logger = LogFactory.getLog(UserController.class);

	@Autowired
	UserDao userDao;
	
	@RequestMapping(value="/user/{id}",method=RequestMethod.GET)
	public @ResponseBody User getUser(@PathVariable String id) {
		return userDao.getById(Integer.valueOf(id)); 
	}
	
	@RequestMapping(value="/user",method=RequestMethod.POST)
	public @ResponseBody int saveUser(@RequestBody User user) {
		logger.info(user);
		UserValidation.validate(user);
		return userDao.save(user);
		
	}
	

}
