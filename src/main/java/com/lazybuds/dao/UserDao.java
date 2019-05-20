package com.lazybuds.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lazybuds.common.Gender;
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

	@SuppressWarnings("unchecked")
	public List<User> search(String location, Gender gender) {

		Query query = sessionFactory.getCurrentSession()
				.createQuery("from User where location=:location and gender=:gender and intro !=null");
		query.setParameter("location", location);
		query.setParameter("gender", gender);

		return query.getResultList();
	}
}
