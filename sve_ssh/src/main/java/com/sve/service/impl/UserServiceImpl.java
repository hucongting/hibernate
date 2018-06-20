package com.sve.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sve.dao.UserDao;
import com.sve.entity.User;
import com.sve.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;
	
	@Override
	public List<User> SelUserList() {
		// TODO Auto-generated method stub
		return userDao.SelUserList();
	}

	@Override
	public User SelUserByUserId(int userid) {
		// TODO Auto-generated method stub
		return userDao.SelUserByUserId(userid);
	}

	@Override
	public boolean isExitsUser(String username) {
		// TODO Auto-generated method stub
		return userDao.isExitsUser(username);
	}

	@Override
	public boolean isExitsUser(String username, String userpwd) {
		// TODO Auto-generated method stub
		return userDao.isExitsUser(username, userpwd);
	}

}
