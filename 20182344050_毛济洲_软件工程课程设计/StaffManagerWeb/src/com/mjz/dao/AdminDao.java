package com.mjz.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mjz.model.Admin;
import com.mjz.model.Clazz;

/**
 * 
 * @author mjz
 *����Ա���ݿ������װ
 */
public class AdminDao extends BaseDao {
	
	public Admin login(String name ,String password){
		String sql = "select * from s_admin where name = '" + name + "' and password = '" + password + "'";
		ResultSet resultSet = query(sql);
		try {
			if(resultSet.next()){
				Admin admin = new Admin();
				admin.setId(resultSet.getInt("id"));
				admin.setName(resultSet.getString("name"));
				admin.setPassword(resultSet.getString("password"));
				admin.setStatus(resultSet.getInt("status"));
				return admin;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public boolean editPassword(Admin admin,String newPassword) {
		// TODO Auto-generated method stub
		String sql = "update s_admin set password = '"+newPassword+"' where id = " + admin.getId();
		return update(sql);
	}
}
