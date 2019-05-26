package com.lazybuds.db.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lazybuds.db.entity.Intro;

@Component
public class UserIntroDao {

	@Autowired
	SessionFactory sessionFactory;

	public int save(Intro intro) {
		return (Integer) sessionFactory.getCurrentSession().save(intro);
	}

	public Intro getById(int id) {
		return sessionFactory.getCurrentSession().get(Intro.class, id);
	}

}
