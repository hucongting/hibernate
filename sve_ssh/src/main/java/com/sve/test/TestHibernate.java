package com.sve.test;

import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.sve.dao.UserDao;
import com.sve.entity.User;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml"})
public class TestHibernate {

	@Autowired
	UserDao userDao;
	
	@Test
	public void test1() {
		List<User> userlist = userDao.SelUserList();
		System.out.println(userlist);
	}
	
	@Test
	public void SelUserByUserId() {
		User user = userDao.SelUserByUserId(1);
		System.out.println(user.getUserid()+"----"+user.getUsername());
	}
	
	@Test
	public void isExitsUser() {
		if(userDao.isExitsUser("hct")) {
			System.out.println("存在");
		}else {System.out.println("不存在");}
	}
	
}
