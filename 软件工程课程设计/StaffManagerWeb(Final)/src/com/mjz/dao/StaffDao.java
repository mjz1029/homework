package com.mjz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mjz.model.Admin;
import com.mjz.model.Department;
import com.mjz.model.Page;
import com.mjz.model.Staff;
import com.mjz.util.StringUtil;

public class StaffDao extends BaseDao {
	public boolean addStaff(Staff staff){
		String sql = "insert into s_staff values(null,'"+staff.getSn()+"','"+staff.getName()+"'";
		sql += ",'" + staff.getPassword() + "'," + staff.getDepartmentId();
		sql += ",'" + staff.getSex() + "','" + staff.getMobile() + "'";
		sql += ",'" + staff.getQq() + "',null)";
		return update(sql);
	}
	public boolean editStaff(Staff staff) {
		// TODO Auto-generated method stub
		String sql = "update s_staff set name = '"+staff.getName()+"'";
		sql += ",sex = '" + staff.getSex() + "'";
		sql += ",mobile = '" + staff.getMobile() + "'";
		sql += ",qq = '" + staff.getQq() + "'";
		sql += ",department_id = " + staff.getDepartmentId();
		sql += " where id = " + staff.getId();
		return update(sql);
	}
	public boolean setStaffPhoto(Staff staff) {
		// TODO Auto-generated method stub
		String sql = "update s_staff set photo = ? where id = ?";
		Connection connection = getConnection();
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setBinaryStream(1, staff.getPhoto());
			prepareStatement.setInt(2, staff.getId());
			return prepareStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return update(sql);
	}
	public boolean deleteStaff(String ids) {
		// TODO Auto-generated method stub
		String sql = "delete from s_staff where id in("+ids+")";
		return update(sql);
	}
	public Staff getStaff(int id){
		String sql = "select * from s_staff where id = " + id;
		Staff staff = null;
		ResultSet resultSet = query(sql);
		try {
			if(resultSet.next()){
				staff = new Staff();
				staff.setId(resultSet.getInt("id"));
				staff.setDepartmentId(resultSet.getInt("department_id"));
				staff.setMobile(resultSet.getString("mobile"));
				staff.setName(resultSet.getString("name"));
				staff.setPassword(resultSet.getString("password"));
				staff.setPhoto(resultSet.getBinaryStream("photo"));
				staff.setQq(resultSet.getString("qq"));
				staff.setSex(resultSet.getString("sex"));
				staff.setSn(resultSet.getString("sn"));
				return staff;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return staff;
	}
	public List<Staff> getStaffList(Staff staff,Page page){
		List<Staff> ret = new ArrayList<Staff>();
		String sql = "select * from s_staff ";
		if(!StringUtil.isEmpty(staff.getName())){
			sql += "and name like '%" + staff.getName() + "%'";
		}
		if(staff.getDepartmentId() != 0){
			sql += " and department_id = " + staff.getDepartmentId();
		}
		if(staff.getId() != 0){
			sql += " and id = " + staff.getId();
		}
		sql += " limit " + page.getStart() + "," + page.getPageSize();
		ResultSet resultSet = query(sql.replaceFirst("and", "where"));
		try {
			while(resultSet.next()){
				Staff s = new Staff();
				s.setId(resultSet.getInt("id"));
				s.setDepartmentId(resultSet.getInt("department_id"));
				s.setMobile(resultSet.getString("mobile"));
				s.setName(resultSet.getString("name"));
				s.setPassword(resultSet.getString("password"));
				s.setPhoto(resultSet.getBinaryStream("photo"));
				s.setQq(resultSet.getString("qq"));
				s.setSex(resultSet.getString("sex"));
				s.setSn(resultSet.getString("sn"));
				ret.add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
	public int getStaffListTotal(Staff staff){
		int total = 0;
		String sql = "select count(*)as total from s_staff ";
		if(!StringUtil.isEmpty(staff.getName())){
			sql += "and name like '%" + staff.getName() + "%'";
		}
		if(staff.getDepartmentId() != 0){
			sql += " and department_id = " + staff.getDepartmentId();
		}
		if(staff.getId() != 0){
			sql += " and id = " + staff.getId();
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
	
	public Staff login(String name ,String password){
		String sql = "select * from s_staff where name = '" + name + "' and password = '" + password + "'";
		ResultSet resultSet = query(sql);
		try {
			if(resultSet.next()){
				Staff staff = new Staff();
				staff.setId(resultSet.getInt("id"));
				staff.setName(resultSet.getString("name"));
				staff.setPassword(resultSet.getString("password"));
				staff.setDepartmentId(resultSet.getInt("department_id"));
				staff.setMobile(resultSet.getString("mobile"));
				staff.setPhoto(resultSet.getBinaryStream("photo"));
				staff.setQq(resultSet.getString("qq"));
				staff.setSex(resultSet.getString("sex"));
				staff.setSn(resultSet.getString("sn"));
				return staff;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public boolean editPassword(Staff staff,String newPassword) {
		// TODO Auto-generated method stub
		String sql = "update s_staff set password = '"+newPassword+"' where id = " + staff.getId();
		return update(sql);
	}
}
