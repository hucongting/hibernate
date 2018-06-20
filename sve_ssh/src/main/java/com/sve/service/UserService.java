package com.sve.service;

import java.util.List;

import com.sve.entity.User;

public interface UserService {
	List<User> SelUserList();
	User SelUserByUserId(int userid);
	boolean isExitsUser(String username);
	boolean isExitsUser(String username,String userpwd);
}
