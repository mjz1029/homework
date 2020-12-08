package com.mjz.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mjz.model.Duty;
import com.mjz.model.Leave;
import com.mjz.model.Page;
import com.mjz.util.StringUtil;

/**
 * ��ٱ����ݿ����
 * @author mjz
 *
 */
public class LeaveDao extends BaseDao {
	/**
	 * ��������Ϣ
	 * @param leave
	 * @return
	 */
	public boolean addLeave(Leave leave){
		String sql = "insert into s_leave values(null,"+leave.getStaffId()+",'"+leave.getInfo()+"',"+Leave.LEAVE_STATUS_WAIT+",'"+leave.getRemark()+"')";
		return update(sql);
	}
	
	/**
	 * �༭��ٵ�
	 * @param leave
	 * @return
	 */
	public boolean editLeave(Leave leave){
		String sql = "update s_leave set staff_id = "+leave.getStaffId()+", info = '"+leave.getInfo()+"',status = "+leave.getStatus()+",remark = '"+leave.getRemark()+"' where id = " + leave.getId();
		return update(sql);
	}
	
	/**
	 * ɾ�������Ϣ
	 * @param id
	 * @return
	 */
	public boolean deleteLeave(int id) {
		// TODO Auto-generated method stub
		String sql = "delete from s_leave where id = " + id ;
		return update(sql);
	}
	
	/**
	 * ��ȡ��ҳ��ٵ���Ϣ�б�
	 * @param leave
	 * @param page
	 * @return
	 */
	public List<Leave> getLeaveList(Leave leave,Page page){
		List<Leave> ret = new ArrayList<Leave>();
		String sql = "select * from s_leave ";
		if(leave.getStaffId() != 0){
			sql += " and staff_id = " + leave.getStaffId() + "";
		}
		sql += " limit " + page.getStart() + "," + page.getPageSize();
		ResultSet resultSet = query(sql.replaceFirst("and", "where"));
		try {
			while(resultSet.next()){
				Leave l = new Leave();
				l.setId(resultSet.getInt("id"));
				l.setStaffId(resultSet.getInt("staff_id"));
				l.setInfo(resultSet.getString("info"));
				l.setStatus(resultSet.getInt("status"));
				l.setRemark(resultSet.getString("remark"));
				ret.add(l);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
	
	/**
	 * ��ȡ�ܼ�¼��
	 * @param leave
	 * @return
	 */
	public int getLeaveListTotal(Leave leave){
		int total = 0;
		String sql = "select count(*)as total from s_leave ";
		if(leave.getStaffId() != 0){
			sql += " and staff_id = " + leave.getStaffId() + "";
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
}
