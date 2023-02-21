package com.mjz.servlet;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
/**
 * 教师信息管理servlet类
 */
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.mjz.dao.StaffDao;
import com.mjz.dao.Staff1Dao;
import com.mjz.model.Page;
import com.mjz.model.Staff;
import com.mjz.model.Staff1;
import com.mjz.util.SnGenerateUtil;

public class Staff1Servlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8421947373714720118L;
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException{
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String method = request.getParameter("method");
		if("toStaff1ListView".equals(method)){
			staff1List(request,response);
		}else if("AddStaff1".equals(method)){
			addStaff1(request,response);
		}else if("Staff1List".equals(method)){
			getStaff1List(request,response);
		}else if("EditStaff1".equals(method)){
			editStaff1(request,response);
		}else if("DeleteStaff1".equals(method)){
			deleteStaff1(request,response);
		}
	}
	private void deleteStaff1(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		String[] ids = request.getParameterValues("ids[]");
		String idStr = "";
		for(String id : ids){
			idStr += id + ",";
		}
		idStr = idStr.substring(0, idStr.length()-1);
		Staff1Dao staff1Dao = new Staff1Dao();
		if(staff1Dao.deleteStaff1(idStr)){
			try {
				response.getWriter().write("success");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				staff1Dao.closeCon();
			}
		}
	}
	private void editStaff1(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		String name = request.getParameter("name");
		int id = Integer.parseInt(request.getParameter("id"));
		String sex = request.getParameter("sex");
		String mobile = request.getParameter("mobile");
		String qq = request.getParameter("qq");
		int departmentId = Integer.parseInt(request.getParameter("departmentid"));
		Staff1 staff1 = new Staff1();
		staff1.setDepartmentId(departmentId);
		staff1.setMobile(mobile);
		staff1.setName(name);
		staff1.setId(id);
		staff1.setQq(qq);
		staff1.setSex(sex);
		Staff1Dao staff1Dao = new Staff1Dao();
		if(staff1Dao.editStaff1(staff1)){
			try {
				response.getWriter().write("success");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				staff1Dao.closeCon();
			}
		}
	}
	private void getStaff1List(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		String name = request.getParameter("staff1Name");
		Integer currentPage = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
		Integer pageSize = request.getParameter("rows") == null ? 999 : Integer.parseInt(request.getParameter("rows"));
		Integer department = request.getParameter("departmentid") == null ? 0 : Integer.parseInt(request.getParameter("departmentid"));
		//获取当前登录用户类型
		int userType = Integer.parseInt(request.getSession().getAttribute("userType").toString());
		Staff1 staff1 = new Staff1();
		staff1.setName(name);
		staff1.setDepartmentId(department);
		if(userType == 3){
			//如果是学生，只能查看自己的信息
			Staff1 currentUser = (Staff1)request.getSession().getAttribute("user");
			staff1.setId(currentUser.getId());
		}
		Staff1Dao staff1Dao = new Staff1Dao();
		List<Staff1> staff1List = staff1Dao.getStaff1List(staff1, new Page(currentPage, pageSize));
		int total = staff1Dao.getStaff1ListTotal(staff1);
		staff1Dao.closeCon();
		response.setCharacterEncoding("UTF-8");
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("total", total);
		ret.put("rows", staff1List);
		try {
			String from = request.getParameter("from");
			if("combox".equals(from)){
				response.getWriter().write(JSONArray.fromObject(staff1List).toString());
			}else{
				response.getWriter().write(JSONObject.fromObject(ret).toString());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void addStaff1(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String sex = request.getParameter("sex");
		String mobile = request.getParameter("mobile");
		String qq = request.getParameter("qq");
		int departmentId = Integer.parseInt(request.getParameter("departmentid"));
		Staff1 staff1 = new Staff1();
		staff1.setDepartmentId(departmentId);
		staff1.setMobile(mobile);
		staff1.setName(name);
		staff1.setPassword(password);
		staff1.setQq(qq);
		staff1.setSex(sex);
		staff1.setSn(SnGenerateUtil.generateStaff1Sn(departmentId));
		Staff1Dao staff1Dao = new Staff1Dao();
		if(staff1Dao.addStaff1(staff1)){
			try {
				response.getWriter().write("success");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				staff1Dao.closeCon();
			}
		}
	}
	private void staff1List(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			request.getRequestDispatcher("view/staff1List.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
