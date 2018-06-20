package com.tedu.webserver.servlet;

import java.io.RandomAccessFile;
import java.util.Arrays;

import com.tedu.webserver.http.HttpRequest;
import com.tedu.webserver.http.HttpResponse;

public class UpdateServlet extends HttpServlet{
	public void service(HttpRequest request, HttpResponse response) {
		String username = request.getParameter("username");
		String userpwd = request.getParameter("userpwd");
		String newuserpwd = request.getParameter("newuserpwd");
		
		try (RandomAccessFile raf = new RandomAccessFile("user.txt", "rw")){
			int check =0;
			
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
					if(pwd.equals(userpwd)){
						System.out.println("update pwd.....");
						raf.seek(32);
						byte[] date = newuserpwd.getBytes("utf-8");
						date = Arrays.copyOf(date, 32);
						raf.write(date);
						check = 0;
					}else {
						check = 2;
					}			
					break;
					
				}else {check=1;}
			}
			if(check == 0) {
				System.out.println("密码修改成功");
				forward("/myweb/update_success.html",request,response);
			}
			if(check == 1) {
				//若没有此用户则跳转no_user.html，提示无此用户
				forward("/myweb/no_user.html", request, response);
			}
			if(check == 2) {
				//修改失败页面
				forward("/myweb/update_fail.html", request, response);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}

	}
}
