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

import com.lazybuds.common.StustusOfMessage;
import com.lazybuds.db.dao.MessageDao;
import com.lazybuds.db.dao.UserDao;
import com.lazybuds.db.dao.UserSessionDao;
import com.lazybuds.db.entity.Message;
import com.lazybuds.db.entity.UserSession;

@Controller
@Transactional
public class MessageController extends AbstractController {

	private static Log logger = LogFactory.getLog(MessageController.class);

	@Autowired
	MessageDao messageDao;

	@Autowired
	UserDao userDao;

	@Autowired
	UserSessionDao userSessionDao;

	@RequestMapping(value = "/user/{toUser}/session", method = RequestMethod.POST)
	public @ResponseBody String createSession(@PathVariable String toUser, @RequestParam String fromUser) {
		// validate session already does not exists.

		UserSession userSession = new UserSession();
		userSession.setFromUser(userDao.getById(Integer.valueOf(fromUser)));
		userSession.setToUser(userDao.getById(Integer.valueOf(toUser)));
		userSession.setStartTime(System.currentTimeMillis());
		return String.valueOf(userSessionDao.save(userSession));
	}

	@RequestMapping(value = "/user/session/{id}", method = RequestMethod.GET)
	public @ResponseBody UserSession getSession(@PathVariable String id) {

		return userSessionDao.getById(Integer.valueOf(id));
	}

	@RequestMapping(value = "/session/{sessionId}/user/{fromUserId}", method = RequestMethod.POST)
	public @ResponseBody int createMessage(@PathVariable String sessionId, @PathVariable String fromUserId,
			@RequestBody com.lazybuds.model.Message messageModel) {

		Message entityMessage = new Message();

		entityMessage.setUserSession(userSessionDao.getById(Integer.valueOf(sessionId)));
		entityMessage.setFromUser(userDao.getById(Integer.valueOf(fromUserId)));
		entityMessage.setSentTime(System.currentTimeMillis());
		entityMessage.setFile(messageModel.getFile());
		entityMessage.setStatus(StustusOfMessage.UPLOADED);

		return messageDao.save(entityMessage);
	}
	
	@RequestMapping(value = "/message/{id}", method = RequestMethod.GET)
	public @ResponseBody Message getMessage(@PathVariable String id) {

			
		return messageDao.getById(Integer.valueOf(id));
	}
	
	
	@RequestMapping(value = "/session/{id}", method = RequestMethod.GET)
	public @ResponseBody List<Message> getAll(@PathVariable String id) {

			
		return messageDao.getAll(Integer.valueOf(id));
	}
	
	
	@RequestMapping(value = "/user/{id}/sessions", method = RequestMethod.GET)
	public @ResponseBody List<UserSession> getAllSession(@PathVariable String id) {
		return messageDao.getAllSession(Integer.valueOf(id));
		
	}
	

	
	
	

}
