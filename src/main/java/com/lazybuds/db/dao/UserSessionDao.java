package com.lazybuds.db.dao;

import java.util.List;

import javax.persistence.Query;

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
	
	@SuppressWarnings("unchecked")
	public List<UserSession> getByUserIds(int toUserId,int fromUserId) {
		Query query = sessionFactory.getCurrentSession().createQuery("from UserSession where (fromUser.userId=:idFrom and toUser.userId=:idTo) or "
				+ "(fromUser.userId=:idTo and toUser.userId=:idFrom)  ");
		query.setParameter("idFrom", fromUserId);
		query.setParameter("idTo", toUserId);
		
		return query.getResultList();
		
	}

}
