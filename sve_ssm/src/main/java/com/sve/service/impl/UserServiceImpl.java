package com.sve.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sve.dao.UserMapper;
import com.sve.entity.User;
import com.sve.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserMapper usermapper;
	
	@Override
	public List<User> SelUserList() {
		// TODO Auto-generated method stub
		return usermapper.SelUserList();
	}

}
