package com.mjz.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mjz.model.Department;
import com.mjz.model.Page;
import com.mjz.util.StringUtil;

/**
 * 
 * @author mjz
 *部门信息数据库操作
 */
public class DepartmentDao extends BaseDao {
	public List<Department> getDepartmentList(Department department,Page page){
		List<Department> ret = new ArrayList<Department>();
		String sql = "select * from s_duty ";
		if(!StringUtil.isEmpty(department.getName())){
			sql += "where name like '%" + department.getName() + "%'";
		}
		sql += " limit " + page.getStart() + "," + page.getPageSize();
		ResultSet resultSet = query(sql);
		try {
			while(resultSet.next()){
				Department cl = new Department();
				cl.setId(resultSet.getInt("id"));
				cl.setName(resultSet.getString("name"));
				cl.setInfo(resultSet.getString("info"));
				ret.add(cl);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
	public int getDepartmentListTotal(Department department){
		int total = 0;
		String sql = "select count(*)as total from s_duty ";
		if(!StringUtil.isEmpty(department.getName())){
			sql += "where name like '%" + department.getName() + "%'";
		}
		ResultSet resultSet = query(sql);
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
	public boolean addDepartment(Department department){
		String sql = "insert into s_duty values(null,'"+department.getName()+"','"+department.getInfo()+"') ";
		return update(sql);
	}
	public boolean deleteDepartment(int id){
		String sql = "delete from s_duty where id = "+id;
		return update(sql);
	}
	public boolean editDepartment(Department department) {
		// TODO Auto-generated method stub
		String sql = "update s_duty set name = '"+department.getName()+"',info = '"+department.getInfo()+"' where id = " + department.getId();
		return update(sql);
	}
	
}
