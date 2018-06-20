package com.sve.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sve.dao.UserDao;
import com.sve.entity.User;

public class TestAction extends ActionSupport {

	/**
	 * Struts2通过以表单相同的name的属性的get,set获得值
	 */
	private String username;
	private String userpwd;
	
	private static final long serialVersionUID = 1L;

	@Autowired
	UserDao userDao;
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("TestAction ..... ");
		ActionContext actionContext = ActionContext.getContext();
		User user = userDao.SelUserByUserId(1);
		System.out.println(user);
		actionContext.put("user", user);
		return "success";
	}
	
	public String selectuser() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("TestAction selectuser ..... ");
		ActionContext actionContext = ActionContext.getContext();
		List<User> user = userDao.SelUserList();
		System.out.println(user);
		actionContext.put("userlist", user);
		return "success";
	}
	
	public String login() {
		return "login";
	}
	
	public String loginSumit() {
		System.out.println(username+"-----"+userpwd);
		
		boolean flag = userDao.isExitsUser(username, userpwd);
		if(flag) {
			return "success";
		}else {
			return "no";
		}
		
	}

	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpwd() {
		return userpwd;
	}

	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}
	
	

}
