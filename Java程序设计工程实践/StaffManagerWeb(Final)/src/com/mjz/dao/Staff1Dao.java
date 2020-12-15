package com.mjz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mjz.model.Page;
import com.mjz.model.Staff;
import com.mjz.model.Staff1;
import com.mjz.util.StringUtil;

/**
 * 
 * @author mjz
 *值班员工表数据库操作
 */
public class Staff1Dao extends BaseDao {
	public boolean addStaff1(Staff1 staff1){
		String sql = "insert into s_staff1 values(null,'"+staff1.getSn()+"','"+staff1.getName()+"'";
		sql += ",'" + staff1.getPassword() + "'," + staff1.getDepartmentId();
		sql += ",'" + staff1.getSex() + "','" + staff1.getMobile() + "'";
		sql += ",'" + staff1.getQq() + "',null)";
		return update(sql);
	}
	public boolean editStaff1(Staff1 staff1) {
		// TODO Auto-generated method stub
		String sql = "update s_staff1 set name = '"+staff1.getName()+"'";
		sql += ",sex = '" + staff1.getSex() + "'";
		sql += ",mobile = '" + staff1.getMobile() + "'";
		sql += ",qq = '" + staff1.getQq() + "'";
		sql += ",department_id = " + staff1.getDepartmentId();
		sql += " where id = " + staff1.getId();
		return update(sql);
	}
	public boolean setStaff1Photo(Staff1 staff1) {
		// TODO Auto-generated method stub
		String sql = "update s_staff1 set photo = ? where id = ?";
		Connection connection = getConnection();
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setBinaryStream(1, staff1.getPhoto());
			prepareStatement.setInt(2, staff1.getId());
			return prepareStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return update(sql);
	}
	public boolean deleteStaff1(String ids) {
		// TODO Auto-generated method stub
		String sql = "delete from s_staff1 where id in("+ids+")";
		return update(sql);
	}
	public Staff1 getStaff1(int id){
		String sql = "select * from s_staff1 where id = " + id;
		Staff1 staff1 = null;
		ResultSet resultSet = query(sql);
		try {
			if(resultSet.next()){
				staff1 = new Staff1();
				staff1.setId(resultSet.getInt("id"));
				staff1.setDepartmentId(resultSet.getInt("department_id"));
				staff1.setMobile(resultSet.getString("mobile"));
				staff1.setName(resultSet.getString("name"));
				staff1.setPassword(resultSet.getString("password"));
				staff1.setPhoto(resultSet.getBinaryStream("photo"));
				staff1.setQq(resultSet.getString("qq"));
				staff1.setSex(resultSet.getString("sex"));
				staff1.setSn(resultSet.getString("sn"));
				return staff1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return staff1;
	}
	public List<Staff1> getStaff1List(Staff1 staff1,Page page){
		List<Staff1> ret = new ArrayList<Staff1>();
		String sql = "select * from s_staff1 ";
		if(!StringUtil.isEmpty(staff1.getName())){
			sql += "and name like '%" + staff1.getName() + "%'";
		}
		if(staff1.getDepartmentId() != 0){
			sql += " and department_id = " + staff1.getDepartmentId();
		}
		if(staff1.getId() !=0 ){
			sql += " and id = " + staff1.getId();
		}
		sql += " limit " + page.getStart() + "," + page.getPageSize();
		ResultSet resultSet = query(sql.replaceFirst("and", "where"));
		try {
			while(resultSet.next()){
				Staff1 t = new Staff1();
				t.setId(resultSet.getInt("id"));
				t.setDepartmentId(resultSet.getInt("department_id"));
				t.setMobile(resultSet.getString("mobile"));
				t.setName(resultSet.getString("name"));
				t.setPassword(resultSet.getString("password"));
				t.setPhoto(resultSet.getBinaryStream("photo"));
				t.setQq(resultSet.getString("qq"));
				t.setSex(resultSet.getString("sex"));
				t.setSn(resultSet.getString("sn"));
				ret.add(t);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
	public int getStaff1ListTotal(Staff1 staff1){
		int total = 0;
		String sql = "select count(*)as total from s_staff1 ";
		if(!StringUtil.isEmpty(staff1.getName())){
			sql += "and name like '%" + staff1.getName() + "%'";
		}
		if(staff1.getDepartmentId() != 0){
			sql += " and department_id = " + staff1.getDepartmentId();
		}
		if(staff1.getId() !=0 ){
			sql += " and id = " + staff1.getId();
		}
		ResultSet resultSet = query(sql.replaceFirst("and", "where"));
		try {
			while(resultSet.next()){
				total = resultSet.getInt("total");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return total;
	}
	public Staff1 login(String name ,String password){
		String sql = "select * from s_staff1 where name = '" + name + "' and password = '" + password + "'";
		ResultSet resultSet = query(sql);
		try {
			if(resultSet.next()){
				Staff1 staff1 = new Staff1();
				staff1.setId(resultSet.getInt("id"));
				staff1.setName(resultSet.getString("name"));
				staff1.setPassword(resultSet.getString("password"));
				staff1.setDepartmentId(resultSet.getInt("department_id"));
				staff1.setMobile(resultSet.getString("mobile"));
				staff1.setPhoto(resultSet.getBinaryStream("photo"));
				staff1.setQq(resultSet.getString("qq"));
				staff1.setSex(resultSet.getString("sex"));
				staff1.setSn(resultSet.getString("sn"));
				return staff1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public boolean editPassword(Staff1 staff1,String newPassword) {
		// TODO Auto-generated method stub
		String sql = "update s_staff1 set password = '"+newPassword+"' where id = " + staff1.getId();
		return update(sql);
	}
}
