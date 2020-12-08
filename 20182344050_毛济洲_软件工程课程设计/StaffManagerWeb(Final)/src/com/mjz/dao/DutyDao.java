package com.mjz.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mjz.model.Department;
import com.mjz.model.Duty;
import com.mjz.model.Page;
import com.mjz.util.StringUtil;

/**
 * 
 * @author mjz
 *班种数据库操作类
 */
public class DutyDao extends BaseDao {
	public boolean addDuty(Duty duty){
		String sql = "insert into s_duty values(null,'"+duty.getName()+"',"+duty.getStaff1Id()+",'"+duty.getDutyDate()+"',0,"+duty.getMaxNum()+",'"+duty.getInfo()+"') ";
		return update(sql);
	}
	
	public List<Duty> getDutyList(Duty duty,Page page){
		List<Duty> ret = new ArrayList<Duty>();
		String sql = "select * from s_duty ";
		if(!StringUtil.isEmpty(duty.getName())){
			sql += "and name like '%" + duty.getName() + "%'";
		}
		if(duty.getStaff1Id() != 0){
			sql += " and staff1_id = " + duty.getStaff1Id() + "";
		}
		sql += " limit " + page.getStart() + "," + page.getPageSize();
		ResultSet resultSet = query(sql.replaceFirst("and", "where"));
		try {
			while(resultSet.next()){
				Duty cl = new Duty();
				cl.setId(resultSet.getInt("id"));
				cl.setName(resultSet.getString("name"));
				cl.setStaff1Id(resultSet.getInt("staff1_id"));
				cl.setDutyDate(resultSet.getString("duty_date"));
				cl.setSelectedNum(resultSet.getInt("selected_num"));
				cl.setMaxNum(resultSet.getInt("max_num"));
				cl.setInfo(resultSet.getString("info"));
				ret.add(cl);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
	
	public int getDutyListTotal(Duty duty){
		int total = 0;
		String sql = "select count(*)as total from s_duty ";
		if(!StringUtil.isEmpty(duty.getName())){
			sql += "and name like '%" + duty.getName() + "%'";
		}
		if(duty.getStaff1Id() != 0){
			sql += " and staff1_id = " + duty.getStaff1Id() + "";
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
	
	public boolean editDuty(Duty duty) {
		// TODO Auto-generated method stub
		String sql = "update s_duty set name = '"+duty.getName()+"',staff1_id = "+duty.getStaff1Id()+",duty_date = '"+duty.getDutyDate()+"',max_num = "+duty.getMaxNum()+" ,info = '"+duty.getInfo()+"' where id = " + duty.getId();
		return update(sql);
	}
	public boolean deleteDuty(String ids) {
		// TODO Auto-generated method stub
		String sql = "delete from s_duty where id in("+ids+")";
		return update(sql);
	}
	/**
	 * 检查该班种是否已选满
	 * @param dutyId
	 * @return
	 */
	public boolean isFull(int dutyId){
		boolean ret = false;
		String sql = "select * from s_duty where selected_num >= max_num and id = " + dutyId;
		ResultSet query = query(sql);
		try {
			if(query.next()){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
	/**
	 * 更新班种已选人数
	 * @param dutyId
	 */
	public void updateDutySelectedNum(int dutyId ,int num){
		String sql = "";
		if(num > 0){
			sql = "update s_duty set selected_num = selected_num + "+ num + " where id = " + dutyId;
		}else{
			sql = "update s_duty set selected_num = selected_num - " + Math.abs(num) + " where id = " + dutyId;
		}
		update(sql);
	}
	
	/**
	 * 获取制定id范围内的班种列表
	 * @param ids
	 * @return
	 */
	public List<Duty> getDuty(String ids){
		List<Duty> ret = new ArrayList<Duty>();
		String sql = "select * from s_duty where id in("+ids+")";
		ResultSet query = query(sql);
		try {
			while(query.next()){
				Duty cl = new Duty();
				cl.setId(query.getInt("id"));
				cl.setName(query.getString("name"));
				cl.setStaff1Id(query.getInt("staff1_id"));
				cl.setDutyDate(query.getString("duty_date"));
				cl.setSelectedNum(query.getInt("selected_num"));
				cl.setMaxNum(query.getInt("max_num"));
				cl.setInfo(query.getString("info"));
				ret.add(cl);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
}
