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
import net.sf.json.JsonConfig;

import com.mjz.dao.DepartmentDao;
import com.mjz.model.Department;
import com.mjz.model.Page;
/**
 * 
 * @author mjz
 *部门信息管理servlet
 */
public class DepartmentServlet extends HttpServlet {
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException{
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String method = request.getParameter("method");
		if("toDepartmentListView".equals(method)){
			departmentList(request,response);
		}else if("getDepartmentList".equals(method)){
			getDepartmentList(request, response);
		}else if("AddDepartment".equals(method)){
			addDepartment(request, response);
		}else if("DeleteDepartment".equals(method)){
			deleteDepartment(request, response);
		}else if("EditDepartment".equals(method)){
			editDepartment(request, response);
		}
	}
	private void editDepartment(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		Integer id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name"); 
		String info = request.getParameter("info");
		Department department = new Department();
		department.setName(name);
		department.setInfo(info);
		department.setId(id);
		DepartmentDao departmentDao = new DepartmentDao();
		if(departmentDao.editDepartment(department)){
			try {
				response.getWriter().write("success");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				departmentDao.closeCon();
			}
		}
	}
	private void deleteDepartment(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		Integer id = Integer.parseInt(request.getParameter("departmentid"));
		DepartmentDao departmentDao = new DepartmentDao();
		if(departmentDao.deleteDepartment(id)){
			try {
				response.getWriter().write("success");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				departmentDao.closeCon();
			}
		}
	}
	private void addDepartment(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		String name = request.getParameter("name"); 
		String info = request.getParameter("info");
		Department department = new Department();
		department.setName(name);
		department.setInfo(info);
		DepartmentDao departmentDao = new DepartmentDao();
		if(departmentDao.addDepartment(department)){
			try {
				response.getWriter().write("success");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				departmentDao.closeCon();
			}
		}
		
	}
	private void departmentList(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			request.getRequestDispatcher("view/departmentList.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void getDepartmentList(HttpServletRequest request,HttpServletResponse response){
		String name = request.getParameter("departmentName");
		Integer currentPage = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
		Integer pageSize = request.getParameter("rows") == null ? 999 : Integer.parseInt(request.getParameter("rows"));
		Department department = new Department();
		department.setName(name);
		DepartmentDao departmentDao = new DepartmentDao();
		List<Department> departmentList = departmentDao.getDepartmentList(department, new Page(currentPage, pageSize));
		int total = departmentDao.getDepartmentListTotal(department);
		departmentDao.closeCon();
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
}
