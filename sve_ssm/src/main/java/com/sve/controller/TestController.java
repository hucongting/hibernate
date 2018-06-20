package com.sve.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sve.dao.UserMapper;
import com.sve.entity.User;

@Controller
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	private UserMapper usermapper;
	
	@RequestMapping("/test")
	public String test() {
		System.out.println("test......");
		return "test";
	}
	
	@RequestMapping("/ssm")
	public String testSSM() {
		List<User> user_list = usermapper.SelUserList();
		System.out.println(user_list);
		return "ssm";
	}
}
