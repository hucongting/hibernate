package com.sve.dao;

import java.util.List;

import com.sve.entity.User;

public interface UserDao {
	List<User> SelUserList();
	User SelUserByUserId(int userid);
	boolean isExitsUser(String username);
	boolean isExitsUser(String username,String userpwd);
}
