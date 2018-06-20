package com.sve.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sve.dao.UserDao;
import com.sve.entity.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public List<User> SelUserList() {
		// TODO Auto-generated method stub
		return sessionFactory.openSession().createQuery("FROM User",User.class).getResultList();
	}

	@Override
	public User SelUserByUserId(int userid) {
		// TODO Auto-generated method stub
		return sessionFactory.openSession().createQuery("FROM User where userid = ?",User.class).setParameter(0, userid).getSingleResult();
	}

	@Override
	public boolean isExitsUser(String username) {
		// TODO Auto-generated method stub
		return sessionFactory.openSession().createQuery("FROM User WHERE username = :name").setParameter("name", username).getResultList().size() > 0 ? true : false;
	}

	@Override
	public boolean isExitsUser(String username, String userpwd) {
		// TODO Auto-generated method stub
		return sessionFactory.openSession().createQuery("FROM User WHERE username = :name and userpwd = :pwd", User.class).setParameter("name", username).setParameter("pwd", userpwd).getResultList().size() > 0 ? true : false;
	}

}
