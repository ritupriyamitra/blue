package com.lazybuds;

import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lazybuds.dao.UserDao;
import com.lazybuds.dao.UserIntroDao;
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

}
