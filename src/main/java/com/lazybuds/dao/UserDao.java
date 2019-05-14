package com.lazybuds.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lazybuds.model.User;

@Component
public class UserDao {

	@Autowired
	SessionFactory sessionFactory;
	
	public int save(User user) {
		return (Integer) sessionFactory.getCurrentSession().save(user);
	}
	
	public User getById(int userId) {
		return sessionFactory.getCurrentSession().get(User.class, userId);
	}
	
}
