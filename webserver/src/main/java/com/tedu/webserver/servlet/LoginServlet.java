package com.tedu.webserver.servlet;

import java.io.RandomAccessFile;

import com.tedu.webserver.http.HttpRequest;
import com.tedu.webserver.http.HttpResponse;

public class LoginServlet extends HttpServlet{
	public void service(HttpRequest request,HttpResponse response) {
		System.out.println("LoginServlet:开始处理用户登录");
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		//2
		try (RandomAccessFile raf = new RandomAccessFile("user.txt", "r")){
			//默认为登录不成功
			boolean check =false;
			
			for(int i=0;i<raf.length()/100;i++){
				raf.seek(i*100);		
				byte[] data = new byte[32];
				raf.read(data);
				String name = new String(data,"UTF-8").trim();
				//找到此用户
				if(name.equals(username)){
					//比对密码
					raf.read(data);
					String pwd = new String(data,"UTF-8").trim();
					if(pwd.equals(password)){
						check = true;
					}			
					break;
				}
			}
			
			if (check) {
				forward("myweb/login_success.html", request, response);
			} else {
				forward("myweb/login_fail.html", request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}



		System.out.println("LoginServlet:处理用户登录完毕");
	}

}
