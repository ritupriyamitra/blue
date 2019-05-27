package com.lazybuds.db.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lazybuds.db.entity.Message;
import com.lazybuds.db.entity.UserSession;

@Component
public class MessageDao {

	@Autowired
	SessionFactory sessionFactory;

	public int save(Message message) {
		return (Integer) sessionFactory.getCurrentSession().save(message);
	}

	public Message getById(int id) {
		return sessionFactory.getCurrentSession().get(Message.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Message> getAll(int sessionId) {
		Query query = sessionFactory.getCurrentSession().createQuery("from Message where userSession.id=:id");
		query.setParameter("id", sessionId);
		return query.getResultList();
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<UserSession> getAllSession(int userId) {
		Query query = sessionFactory.getCurrentSession().createQuery("from UserSession where fromUser.userId=:id or toUser.userId=:id");
		query.setParameter("id", userId);
		return query.getResultList();
	}

	

	
}
