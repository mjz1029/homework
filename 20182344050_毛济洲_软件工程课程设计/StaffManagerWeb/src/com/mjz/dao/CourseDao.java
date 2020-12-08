package com.mjz.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mjz.model.Clazz;
import com.mjz.model.Course;
import com.mjz.model.Page;
import com.mjz.util.StringUtil;

/**
 * 
 * @author mjz
 *班种数据库操作类
 */
public class CourseDao extends BaseDao {
	public boolean addCourse(Course course){
		String sql = "insert into s_duty values(null,'"+course.getName()+"',"+course.getTeacherId()+",'"+course.getCourseDate()+"',0,"+course.getMaxNum()+",'"+course.getInfo()+"') ";
		return update(sql);
	}
	
	public List<Course> getCourseList(Course course,Page page){
		List<Course> ret = new ArrayList<Course>();
		String sql = "select * from s_duty ";
		if(!StringUtil.isEmpty(course.getName())){
			sql += "and name like '%" + course.getName() + "%'";
		}
		if(course.getTeacherId() != 0){
			sql += " and teacher_id = " + course.getTeacherId() + "";
		}
		sql += " limit " + page.getStart() + "," + page.getPageSize();
		ResultSet resultSet = query(sql.replaceFirst("and", "where"));
		try {
			while(resultSet.next()){
				Course cl = new Course();
				cl.setId(resultSet.getInt("id"));
				cl.setName(resultSet.getString("name"));
				cl.setTeacherId(resultSet.getInt("teacher_id"));
				cl.setCourseDate(resultSet.getString("course_date"));
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
	
	public int getCourseListTotal(Course course){
		int total = 0;
		String sql = "select count(*)as total from s_duty ";
		if(!StringUtil.isEmpty(course.getName())){
			sql += "and name like '%" + course.getName() + "%'";
		}
		if(course.getTeacherId() != 0){
			sql += " and teacher_id = " + course.getTeacherId() + "";
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
	
	public boolean editCourse(Course course) {
		// TODO Auto-generated method stub
		String sql = "update s_duty set name = '"+course.getName()+"',teacher_id = "+course.getTeacherId()+",course_date = '"+course.getCourseDate()+"',max_num = "+course.getMaxNum()+" ,info = '"+course.getInfo()+"' where id = " + course.getId();
		return update(sql);
	}
	public boolean deleteCourse(String ids) {
		// TODO Auto-generated method stub
		String sql = "delete from s_duty where id in("+ids+")";
		return update(sql);
	}
	/**
	 * 检查该班种是否已选满
	 * @param courseId
	 * @return
	 */
	public boolean isFull(int courseId){
		boolean ret = false;
		String sql = "select * from s_duty where selected_num >= max_num and id = " + courseId;
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
	 * @param courseId
	 */
	public void updateCourseSelectedNum(int courseId ,int num){
		String sql = "";
		if(num > 0){
			sql = "update s_duty set selected_num = selected_num + "+ num + " where id = " + courseId;
		}else{
			sql = "update s_duty set selected_num = selected_num - " + Math.abs(num) + " where id = " + courseId;
		}
		update(sql);
	}
	
	/**
	 * 获取制定id范围内的班种列表
	 * @param ids
	 * @return
	 */
	public List<Course> getCourse(String ids){
		List<Course> ret = new ArrayList<Course>();
		String sql = "select * from s_duty where id in("+ids+")";
		ResultSet query = query(sql);
		try {
			while(query.next()){
				Course cl = new Course();
				cl.setId(query.getInt("id"));
				cl.setName(query.getString("name"));
				cl.setTeacherId(query.getInt("teacher_id"));
				cl.setCourseDate(query.getString("course_date"));
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
