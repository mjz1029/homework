package com.book.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbUtil {
	
	private String url = "jdbc:mysql://localhost:3306/work?serverTimezone=UTC";
	private String username = "root";
	private String password = "mysql1234";
	
	//�������ݿⷽ��
	public Connection getCon() throws Exception {
		//Class.forName("com.mysql.jdbc.Driver");
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = (Connection) DriverManager.getConnection(url, username, password);
		return con;
	}
	//�ر����ݿⷽ��
	public void closeCon(java.sql.Connection con) throws Exception {
		
		if(con != null) {
			con.close();
		}
	}
	

}
