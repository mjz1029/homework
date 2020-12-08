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

import com.mjz.dao.DutyDao;
import com.mjz.dao.Staff1Dao;
import com.mjz.model.Duty;
import com.mjz.model.Page;

public class DutyServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1991371597134855732L;
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException{
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String method = request.getParameter("method");
		if("toDutyListView".equals(method)){
			try {
				request.getRequestDispatcher("view/dutyList.jsp").forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if("AddDuty".equals(method)){
			addDuty(request,response);
		}else if("DutyList".equals(method)){
			getDutyList(request,response);
		}else if("EditDuty".equals(method)){
			editDuty(request,response);
		}else if("DeleteDuty".equals(method)){
			deleteDuty(request,response);
		}
	}
	private void deleteDuty(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		String[] ids = request.getParameterValues("ids[]");
		String idStr = "";
		for(String id : ids){
			idStr += id + ",";
		}
		idStr = idStr.substring(0, idStr.length()-1);
		DutyDao dutyDao = new DutyDao();
		if(dutyDao.deleteDuty(idStr)){
			try {
				response.getWriter().write("success");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				dutyDao.closeCon();
			}
		}
	}
	private void editDuty(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		String name = request.getParameter("name");
		int staff1Id = Integer.parseInt(request.getParameter("staff1id").toString());
		int maxNum = Integer.parseInt(request.getParameter("maxnum").toString());
		int id = Integer.parseInt(request.getParameter("id").toString());
		String dutyDate = request.getParameter("dutyDate");
		String info = request.getParameter("info");
		Duty duty = new Duty();
		duty.setId(id);
		duty.setName(name);
		duty.setStaff1Id(staff1Id);
		duty.setInfo(info);
		duty.setDutyDate(dutyDate);
		duty.setMaxNum(maxNum);
		DutyDao dutyDao = new DutyDao();
		String msg = "error";
		if(dutyDao.editDuty(duty)){
			msg = "success";
		}
		try {
			response.getWriter().write(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			dutyDao.closeCon();
		}
	}
	private void getDutyList(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		String name = request.getParameter("name");
		int staff1Id = request.getParameter("staff1id") == null ? 0 : Integer.parseInt(request.getParameter("staff1id").toString());
		Integer currentPage = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
		Integer pageSize = request.getParameter("rows") == null ? 999 : Integer.parseInt(request.getParameter("rows"));
		Duty duty = new Duty();
		duty.setName(name);
		duty.setStaff1Id(staff1Id);
		DutyDao dutyDao = new DutyDao();
		List<Duty> dutyList = dutyDao.getDutyList(duty, new Page(currentPage, pageSize));
		int total = dutyDao.getDutyListTotal(duty);
		dutyDao.closeCon();
		response.setCharacterEncoding("UTF-8");
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("total", total);
		ret.put("rows", dutyList);
		try {
			String from = request.getParameter("from");
			if("combox".equals(from)){
				response.getWriter().write(JSONArray.fromObject(dutyList).toString());
			}else{
				response.getWriter().write(JSONObject.fromObject(ret).toString());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void addDuty(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		String name = request.getParameter("name");
		int staff1Id = Integer.parseInt(request.getParameter("staff1id").toString());
		int maxNum = Integer.parseInt(request.getParameter("maxnum").toString());
		String dutyDate = request.getParameter("duty_date");
		String info = request.getParameter("info");
		Duty duty = new Duty();
		duty.setName(name);
		duty.setStaff1Id(staff1Id);
		duty.setInfo(info);
		duty.setMaxNum(maxNum);
		duty.setDutyDate(dutyDate);
		DutyDao dutyDao = new DutyDao();
		String msg = "error";
		if(dutyDao.addDuty(duty)){
			msg = "success";
		}
		try {
			response.getWriter().write(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			dutyDao.closeCon();
		}
	}
	
}
