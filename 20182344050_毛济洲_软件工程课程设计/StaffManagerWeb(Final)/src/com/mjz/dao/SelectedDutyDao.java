package com.mjz.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mjz.model.Duty;
import com.mjz.model.Page;
import com.mjz.model.SelectedDuty;

/**
 * 
 * @author mjz
 *选班表数据库操作封装
 */
public class SelectedDutyDao extends BaseDao {
	public List<SelectedDuty> getSelectedDutyList(SelectedDuty selectedDuty,Page page){
		List<SelectedDuty> ret = new ArrayList<SelectedDuty>();
		String sql = "select * from s_selected_duty ";
		if(selectedDuty.getStaffId() != 0){
			sql += " and staff_id = " + selectedDuty.getStaffId();
		}
		if(selectedDuty.getDutyId() != 0){
			sql += " and duty_id = " + selectedDuty.getDutyId();
		}
		sql += " limit " + page.getStart() + "," + page.getPageSize();
		sql = sql.replaceFirst("and", "where");
		ResultSet resultSet = query(sql);
		try {
			while(resultSet.next()){
				SelectedDuty cl = new SelectedDuty();
				cl.setId(resultSet.getInt("id"));
				cl.setDutyId(resultSet.getInt("duty_id"));
				cl.setStaffId(resultSet.getInt("staff_id"));
				ret.add(cl);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
	public int getSelectedDutyListTotal(SelectedDuty selectedDuty){
		int total = 0;
		String sql = "select count(*)as total from s_selected_duty ";
		if(selectedDuty.getStaffId() != 0){
			sql += " and staff_id = " + selectedDuty.getStaffId();
		}
		if(selectedDuty.getDutyId() != 0){
			sql += " and duty_id = " + selectedDuty.getDutyId();
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
	/**
	 * 检查员工是否已经选择该门班种
	 * @param staffId
	 * @param dutyId
	 * @return
	 */
	public boolean isSelected(int staffId,int dutyId){
		boolean ret = false;
		String sql = "select * from s_selected_duty where staff_id = " + staffId + " and duty_id = " + dutyId;
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
	 * 添加选班信息
	 * @param selectedDuty
	 * @return
	 */
	public boolean addSelectedDuty(SelectedDuty selectedDuty){
		String sql = "insert into s_selected_duty values(null,"+selectedDuty.getStaffId()+","+selectedDuty.getDutyId()+")";
		return update(sql);
	}
	/**
	 * 删除所选班
	 * @param id
	 * @return
	 */
	public boolean deleteSelectedDuty(int id){
		String sql = "delete from s_selected_duty where id = " + id;
		return update(sql);
	}
	/**
	 * 获取一条选班数据
	 * @param id
	 * @return
	 */
	public SelectedDuty getSelectedDuty(int id){
		SelectedDuty ret = null;
		String sql = "select * from s_selected_duty where id = " + id;
		ResultSet query = query(sql);
		try {
			if(query.next()){
				ret = new SelectedDuty();
				ret.setId(query.getInt("id"));
				ret.setDutyId(query.getInt("duty_id"));
				ret.setStaffId(query.getInt("staff_id"));
				return ret;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
}
