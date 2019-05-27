package com.lazybuds;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lazybuds.common.StringUtil;
import com.lazybuds.common.StustusOfMessage;
import com.lazybuds.db.dao.MessageDao;
import com.lazybuds.db.dao.UserDao;
import com.lazybuds.db.dao.UserSessionDao;
import com.lazybuds.db.entity.Message;
import com.lazybuds.db.entity.UserSession;
import com.lazybuds.exceptions.InternalException;
import com.lazybuds.exceptions.ValidationException;

@Controller
@Transactional
public class MessageController extends AbstractController {

	@Value( "${local.fileStore}" )
	private String UPLOADED_FOLDER;

	@SuppressWarnings("unused")
	private static Log logger = LogFactory.getLog(MessageController.class);

	@Autowired
	private MessageDao messageDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private UserSessionDao userSessionDao;

	@RequestMapping(value = "/user/{toUser}/session", method = RequestMethod.POST)
	public @ResponseBody String createSession(@PathVariable String toUser, @RequestParam String fromUser) {

		List<UserSession> usersessionList = userSessionDao.getByUserIds(Integer.valueOf(toUser),
				Integer.valueOf(fromUser));
		if (usersessionList.size() > 0) {
			UserSession userSession = usersessionList.get(0);
			return String.valueOf(userSession.getId());
		} else {

			UserSession userSession = new UserSession();
			userSession.setFromUser(userDao.getById(Integer.valueOf(fromUser)));
			userSession.setToUser(userDao.getById(Integer.valueOf(toUser)));
			userSession.setStartTime(System.currentTimeMillis());
			return String.valueOf(userSessionDao.save(userSession));
		}
	}

	@RequestMapping(value = "/user/session/{id}", method = RequestMethod.GET)
	public @ResponseBody UserSession getSession(@PathVariable String id) {

		return userSessionDao.getById(Integer.valueOf(id));
	}

	@RequestMapping(value = "/session/{sessionId}/user/{fromUserId}", method = RequestMethod.POST)
	public @ResponseBody int createMessage(@PathVariable String sessionId, @PathVariable String fromUserId,
			@RequestBody com.lazybuds.model.Message messageModel) {

		if (StringUtil.isNullOrEmpty(messageModel.getFile())) {
			throw new ValidationException("File is empty");
		}
		String fileName = UUID.randomUUID().toString();

		try {
			byte[] bytes = Base64.getDecoder().decode(messageModel.getFile());

			Path path = Paths.get(UPLOADED_FOLDER + "/" + fileName);
			Files.write(path, bytes);

		} catch (IOException e) {
			throw new InternalException("Error in storing file");
		}
		Message entityMessage = new Message();

		entityMessage.setUserSession(userSessionDao.getById(Integer.valueOf(sessionId)));
		entityMessage.setFromUser(userDao.getById(Integer.valueOf(fromUserId)));
		entityMessage.setSentTime(System.currentTimeMillis());
		entityMessage.setFile(fileName);
		entityMessage.setStatus(StustusOfMessage.UPLOADED);

		return messageDao.save(entityMessage);
	}

	@RequestMapping(value = "/message/{id}", method = RequestMethod.GET)
	public @ResponseBody com.lazybuds.model.Message getMessage(@PathVariable String id) {
		Message message = messageDao.getById(Integer.valueOf(id));

		Path path = Paths.get(UPLOADED_FOLDER + "/" + message.getFile());
		com.lazybuds.model.Message responseMessage = new com.lazybuds.model.Message();
		try {

			byte[] bytes = Files.readAllBytes(path);
			responseMessage.setFile(new String(Base64.getEncoder().encode(bytes)));
			responseMessage.setId(message.getId());
			responseMessage.setFromUser(message.getFromUser());
			responseMessage.setSentTime(message.getSentTime());
			responseMessage.setStatus(message.getStatus());

		} catch (IOException e) {
			throw new InternalException("Error in reading file");
		}
		return responseMessage;
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
