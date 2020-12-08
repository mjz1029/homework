package com.book.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.book.model.Admin;

public class AdminDao {
	
	//管理员登录验证
	public Admin adminLogin(Connection con, Admin adminMessage) throws Exception {
	
		Admin resultAdmin = null;
		String sql = "select * from admin where adminId = ? and password = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1,adminMessage.getAdminId());
		pstmt.setString(2,adminMessage.getPassword());
		ResultSet rs = pstmt.executeQuery();
		
 		if(rs.next()) {
			resultAdmin = new Admin();
		    resultAdmin.setAdminId(rs.getString("adminId"));
			resultAdmin.setAdminname(rs.getString("adminname"));
			resultAdmin.setPassword(rs.getString("password"));
		}	
 		
		return resultAdmin;
	}
	
}
