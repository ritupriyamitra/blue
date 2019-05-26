package com.lazybuds.db.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lazybuds.db.entity.UserSession;

@Component
public class UserSessionDao {

	@Autowired
	SessionFactory sessionFactory;

	public int save(UserSession userSession) {
		return (Integer) sessionFactory.getCurrentSession().save(userSession);
	}

	public UserSession getById(int id) {
		return sessionFactory.getCurrentSession().get(UserSession.class, id);
	}

}
