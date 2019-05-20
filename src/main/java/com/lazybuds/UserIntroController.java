package com.lazybuds;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lazybuds.common.Gender;
import com.lazybuds.dao.UserDao;
import com.lazybuds.dao.UserIntroDao;
import com.lazybuds.exceptions.DataNotFoundException;
import com.lazybuds.exceptions.ValidationException;
import com.lazybuds.model.Intro;
import com.lazybuds.model.User;

@Controller
@Transactional
public class UserIntroController extends AbstractController {

	private static Log logger = LogFactory.getLog(UserIntroController.class);

	@Autowired
	UserIntroDao userintroDao;

	@Autowired
	UserDao userDao;

	@RequestMapping(value = "/user/{id}/intro", method = RequestMethod.GET)
	public @ResponseBody Intro getIntro(@PathVariable String id) {
		User user = userDao.getById(Integer.valueOf(id));
		if (user == null) {
			throw new ValidationException("User not found");
		}
		return user.getIntro();
	}

	@RequestMapping(value = "/user/{id}/intro", method = RequestMethod.POST)
	public @ResponseBody int saveUser(@RequestBody Intro intro, @PathVariable String id) {
		User user = userDao.getById(Integer.valueOf(id));
		if (user == null) {
			throw new ValidationException("User not found");
		}
		logger.info(intro);
		int introId = userintroDao.save(intro);

		user.setIntro(intro);
		userDao.save(user);

		return introId;
	}
	
	
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public @ResponseBody List<User>  getUserByLocation(@RequestParam String location , @RequestParam Gender gender) {
		List<User> users = userDao.search(location,gender);
		if (users.isEmpty()) {
			throw new DataNotFoundException("Users not found by that location and gender");
		}
		return users;
	}

}
