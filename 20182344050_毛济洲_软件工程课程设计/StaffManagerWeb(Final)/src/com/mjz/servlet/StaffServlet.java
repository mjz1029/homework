package com.mjz.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.mjz.dao.DepartmentDao;
import com.mjz.dao.StaffDao;
import com.mjz.model.Department;
import com.mjz.model.Page;
import com.mjz.model.Staff;
import com.mjz.util.SnGenerateUtil;
/**
 * 
 * @author mjz
 *员工信息管理功能实现servlet
 */
public class StaffServlet extends HttpServlet {
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException{
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String method = request.getParameter("method");
		if("toStaffListView".equals(method)){
			staffList(request,response);
		}else if("AddStaff".equals(method)){
			addStaff(request,response);
		}else if("StaffList".equals(method)){
			getStaffList(request,response);
		}else if("EditStaff".equals(method)){
			editStaff(request,response);
		}else if("DeleteStaff".equals(method)){
			deleteStaff(request,response);
		}
	}
	private void deleteStaff(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		String[] ids = request.getParameterValues("ids[]");
		String idStr = "";
		for(String id : ids){
			idStr += id + ",";
		}
		idStr = idStr.substring(0, idStr.length()-1);
		StaffDao staffDao = new StaffDao();
		if(staffDao.deleteStaff(idStr)){
			try {
				response.getWriter().write("success");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				staffDao.closeCon();
			}
		}
	}
	private void editStaff(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		String name = request.getParameter("name");
		int id = Integer.parseInt(request.getParameter("id"));
		String sex = request.getParameter("sex");
		String mobile = request.getParameter("mobile");
		String qq = request.getParameter("qq");
		int departmentId = Integer.parseInt(request.getParameter("departmentid"));
		Staff staff = new Staff();
		staff.setDepartmentId(departmentId);
		staff.setMobile(mobile);
		staff.setName(name);
		staff.setId(id);
		staff.setQq(qq);
		staff.setSex(sex);
		StaffDao staffDao = new StaffDao();
		if(staffDao.editStaff(staff)){
			try {
				response.getWriter().write("success");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				staffDao.closeCon();
			}
		}
	}
	private void getStaffList(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		String name = request.getParameter("staffName");
		Integer currentPage = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
		Integer pageSize = request.getParameter("rows") == null ? 999 : Integer.parseInt(request.getParameter("rows"));
		Integer department = request.getParameter("departmentid") == null ? 0 : Integer.parseInt(request.getParameter("departmentid"));
		//获取当前登录用户类型
		int userType = Integer.parseInt(request.getSession().getAttribute("userType").toString());
		Staff staff = new Staff();
		staff.setName(name);
		staff.setDepartmentId(department);
		if(userType == 2){
			//如果是学生，只能查看自己的信息
			Staff currentUser = (Staff)request.getSession().getAttribute("user");
			staff.setId(currentUser.getId());
		}
		StaffDao staffDao = new StaffDao();
		List<Staff> departmentList = staffDao.getStaffList(staff, new Page(currentPage, pageSize));
		int total = staffDao.getStaffListTotal(staff);
		staffDao.closeCon();
		response.setCharacterEncoding("UTF-8");
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("total", total);
		ret.put("rows", departmentList);
		try {
			String from = request.getParameter("from");
			if("combox".equals(from)){
				response.getWriter().write(JSONArray.fromObject(departmentList).toString());
			}else{
				response.getWriter().write(JSONObject.fromObject(ret).toString());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void addStaff(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String sex = request.getParameter("sex");
		String mobile = request.getParameter("mobile");
		String qq = request.getParameter("qq");
		int departmentId = Integer.parseInt(request.getParameter("departmentid"));
		Staff staff = new Staff();
		staff.setDepartmentId(departmentId);
		staff.setMobile(mobile);
		staff.setName(name);
		staff.setPassword(password);
		staff.setQq(qq);
		staff.setSex(sex);
		staff.setSn(SnGenerateUtil.generateSn(departmentId));
		StaffDao staffDao = new StaffDao();
		if(staffDao.addStaff(staff)){
			try {
				response.getWriter().write("success");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				staffDao.closeCon();
			}
		}
	}
	private void staffList(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		try {
			request.getRequestDispatcher("view/staffList.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
