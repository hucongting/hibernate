package com.tedu.webserver.core;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务端主类
 * @author soft01
 *
 */
public class WebServer {
	
	private ServerSocket server;
	
	/**
	 * 初始化WebServer
	 */
	public WebServer() {
		try {
			System.out.println("正在启动服务端。。。");
			server = new ServerSocket(8088);
			System.out.println("启动服务端完毕！");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 服务端启动方法
	 */
	public void start() {
		try {
			while (true) {
				System.out.println("等待客户端连接。。。");
				Socket socket = server.accept();
				System.out.println("一个客户端连接了。。。");
				//启动线程处理该客户端请求
				ClientHandler handler = new ClientHandler(socket);
				Thread t = new Thread(handler);
				t.start();
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		WebServer server = new WebServer();
		server.start();
	}
}
