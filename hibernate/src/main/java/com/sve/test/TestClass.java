package com.sve.test;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.junit.Test;

import com.sve.entity.User;

/**
 *  测试类
 * @author soft01
 *
 */
public class TestClass {
	
	@Test
	public void findUserById() {
		
		//hibernate框架加载hibernate.cfg.xml配置文件
		Configuration cfg = new Configuration().configure();
		SessionFactory factory = cfg.buildSessionFactory();
		//得到一个Connection
		Session session = factory.openSession();
		//开启事务
		session.beginTransaction();
		
		User user = session.load(User.class, 4);
		System.out.println(user.getUsername());
		
		//事务提交
		session.getTransaction().commit();
		session.close();
		factory.close();
	}
	
	
	
	@Test
	public void saveUser() {
		
		User user = new User();
		user.setUsername("test");
		user.setUserpwd("123123");
		
		//hibernate框架加载hibernate.cfg.xml配置文件
		Configuration cfg = new Configuration().configure();
		SessionFactory factory = cfg.buildSessionFactory();
		//得到一个Connection
		Session session = factory.openSession();
		//开启事务
		session.beginTransaction();
		
		session.save(user);
		
		//事务提交
		session.getTransaction().commit();
		session.close();
		factory.close();
	}
	
	
	@Test
	public void updateUser() {
		
		//hibernate框架加载hibernate.cfg.xml配置文件
		Configuration cfg = new Configuration().configure();
		SessionFactory factory = cfg.buildSessionFactory();
		//得到一个Connection
		Session session = factory.openSession();
		//开启事务
		session.beginTransaction();
		
		User user = session.get(User.class, 6);
		user.setUserpwd("456456");
		session.update(user);
		
		//事务提交
		session.getTransaction().commit();
		session.close();
		factory.close();
	}
	
	@Test
	public void findUserList() {
		
		//hibernate框架加载hibernate.cfg.xml配置文件
		Configuration cfg = new Configuration().configure();
		SessionFactory factory = cfg.buildSessionFactory();
		//得到一个Connection
		Session session = factory.openSession();
		//开启事务
		session.beginTransaction();
		
		/**
		 *  注意Hibernate5 新特性
		 *  
		 *  不要导入import org.hibernate.Query包（已经过期）
		 *  用Hibernate5 中的 import org.hibernate.query.Query;
		 *  list.list() （已经过期） ----->   用getResultList() （Hibernate5）
		 */
		Query<User> list =  session.createQuery("FROM User",User.class);
		List<User> userlist = list.getResultList();
		System.out.println(userlist);
		for(User u : userlist) {
			System.out.println(u.getUserid()+"    "+u.getUsername());
		}
		
		//事务提交
		session.getTransaction().commit();
		session.close();
		factory.close();
	}
	
	
	/**
	 * 删除
	 */
	@Test
	public void deleteUser() {
		
		//hibernate框架加载hibernate.cfg.xml配置文件
		Configuration cfg = new Configuration().configure();
		SessionFactory factory = cfg.buildSessionFactory();
		//得到一个Connection
		Session session = factory.openSession();
		//开启事务
		session.beginTransaction();
		
		User user = session.get(User.class, 7);
		session.delete(user);
		
		//事务提交
		session.getTransaction().commit();
		session.close();
		factory.close();
	}
	
	
	
	//--------------------------HQL-----------------------
	
	@Test
	public void SelUserHQL() {
		
		//hibernate框架加载hibernate.cfg.xml配置文件
		Configuration cfg = new Configuration().configure();
		SessionFactory factory = cfg.buildSessionFactory();
		//得到一个Connection
		Session session = factory.openSession();
		//开启事务
		session.beginTransaction();
		
		
		String hql = "FROM User";
		Query<User> query = session.createQuery(hql, User.class).setFirstResult(0).setMaxResults(3);
		List<User> querylist = query.getResultList();
		for(User u : querylist) {
			System.out.println(u.getUserid()+"--->"+u.getUsername());
		}

		//事务提交
		session.getTransaction().commit();
		session.close();
		factory.close();
	}
	
	
	/**
	 * 索引占位符
	 */
	@Test
	public void SelUserHQLByUserId() {
		
		//hibernate框架加载hibernate.cfg.xml配置文件
		Configuration cfg = new Configuration().configure();
		SessionFactory factory = cfg.buildSessionFactory();
		//得到一个Connection
		Session session = factory.openSession();
		//开启事务
		session.beginTransaction();
		try {
			
			String hql = "FROM User WHERE userid = ?";
			Query<User> query = session.createQuery(hql, User.class).setParameter(0, 2);
			User u = query.getSingleResult();
			System.out.println(u.getUserid()+"--->"+u.getUsername());
			
			//事务提交
			session.getTransaction().commit();
			session.close();
			factory.close();
		}catch(Exception e) {
			session.getTransaction().rollback();
		}finally {
			factory.close();
			session.close();
		}
		
		
	}
		
	
	
	
	@Test
	public void SelUserHQLByUserId2() {
		
		//hibernate框架加载hibernate.cfg.xml配置文件
		Configuration cfg = new Configuration().configure();
		SessionFactory factory = cfg.buildSessionFactory();
		//得到一个Connection
		Session session = factory.openSession();
		//开启事务
		session.beginTransaction();
		
		String hql = "FROM User WHERE userid in :id";
		User u = session.createQuery(hql, User.class).setParameter("id", 4).getSingleResult();

		System.out.println(u.getUserid()+"--->"+u.getUsername());
		
		//事务提交
		session.getTransaction().commit();
		session.close();
		factory.close();
	}
	
	
	@Test
	public void UpdateUserHQL() {
		
		//hibernate框架加载hibernate.cfg.xml配置文件
		Configuration cfg = new Configuration().configure();
		SessionFactory factory = cfg.buildSessionFactory();
		//得到一个Connection
		Session session = factory.openSession();
		//开启事务
		Transaction tx = session.beginTransaction();
		
		String hql = "Update User SET userpwd = ? WHERE userid = ?";
		Query<?> query = session.createQuery(hql).setParameter(0, "111111").setParameter(1, 6);
		int result = query.executeUpdate();
		System.out.println(result);
		
		//事务提交
		tx.commit();
		session.close();
		factory.close();
	}
	
	
	
}
