package com.book.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.book.model.User;
import com.book.util.StringUtil;

public class UserDao {
	//ע�ᣬҲ�������
	public int add(Connection con, User userMessage) throws Exception {
		
		//�������ݿ��в�ѯ����userId�����鵽�ˣ�����0������ע��ʧ��
		String sqlid = "select * from user where userId = ?";
		PreparedStatement pstmtid = con.prepareStatement(sqlid);
		pstmtid.setString(1,userMessage.getUserId());
		ResultSet rsid = pstmtid.executeQuery();
		if(rsid.next()) {
			return 0;
		}
		//�����ݿ���û��userId������Բ���
		String sql = "insert into user values(?,?,?)";
		PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(sql);
		
		pstmt.setString(1, userMessage.getUserId());		
		pstmt.setString(2, userMessage.getUsername());
		pstmt.setString(3, userMessage.getPassword());
		
		return pstmt.executeUpdate();	
	}
	
	//��¼��֤
	public User login(Connection con, User userMessage) throws Exception {
	
		User resultUser = null;
		String sql = "select * from user where userId = ? and password = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1,userMessage.getUserId());
		pstmt.setString(2,userMessage.getPassword());
		ResultSet rs = pstmt.executeQuery();
		
 		if(rs.next()) {
			resultUser = new User();
		    resultUser.setUserId(rs.getString("userId"));
			resultUser.setUsername(rs.getString("username"));
			resultUser.setPassword(rs.getString("password"));
		}	
		return  resultUser;
	}
	
	//��ѯ�û�
	public  ResultSet userLook(Connection con, User userMessage) throws Exception {
		
		StringBuffer sb = new StringBuffer("select * from user where userId = userId ");
		
		//��sb����ӣ�������ģ����ѯ
		//�˺�ģ����ѯ
		if(StringUtil.isNotEmpty(userMessage.getUserId())) { 
			sb.append(" and userId like '%" + userMessage.getUserId() + "%'");	
		}
		
		//�û���ģ����ѯ
		if(StringUtil.isNotEmpty(userMessage.getUsername())) { 
			sb.append(" and user.username like '%" + userMessage.getUsername() + "%'");		
		}
		
		PreparedStatement pstmt=(PreparedStatement) con.prepareStatement(sb.toString());	
		//ResultSet rs = pstmt.executeQuery();

		return pstmt.executeQuery();	
	}
	
	//ɾ���û�
	public int userDelete(Connection con, String id) throws Exception {
		
		String sql = "delete from user where userId = ?";	
		PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(sql);	
		pstmt.setString(1, id);	
		
		return pstmt.executeUpdate();
	}
}
