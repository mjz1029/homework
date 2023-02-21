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
import com.mjz.dao.SelectedDutyDao;
import com.mjz.model.Duty;
import com.mjz.model.Page;
import com.mjz.model.SelectedDuty;
import com.mjz.model.Staff;

public class SelectedDutyServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7120913402001186955L;

	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException{
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String method = request.getParameter("method");
		if("toSelectedDutyListView".equals(method)){
			try {
				request.getRequestDispatcher("view/selectedDutyList.jsp").forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if("AddSelectedDuty".equals(method)){
			addSelectedDuty(request,response);
		}else if("SelectedDutyList".equals(method)){
			getSelectedDutyList(request,response);
		}else if("DeleteSelectedDuty".equals(method)){
			deleteSelectedDuty(request,response);
		}
	}
	private void deleteSelectedDuty(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		SelectedDutyDao selectedDutyDao = new SelectedDutyDao();
		SelectedDuty selectedDuty = selectedDutyDao.getSelectedDuty(id);
		String msg = "success";
		if(selectedDuty == null){
			msg = "not found";
			response.getWriter().write(msg);
			selectedDutyDao.closeCon();
			return;
		}
		if(selectedDutyDao.deleteSelectedDuty(selectedDuty.getId())){
			DutyDao dutyDao = new DutyDao();
			dutyDao.updateDutySelectedNum(selectedDuty.getDutyId(), -1);
			dutyDao.closeCon();
		}else{
			msg = "error";
		}
		selectedDutyDao.closeCon();
		response.getWriter().write(msg);
	}
	private void addSelectedDuty(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		int staffId = request.getParameter("staffid") == null ? 0 : Integer.parseInt(request.getParameter("staffid").toString());
		int dutyId = request.getParameter("dutyid") == null ? 0 : Integer.parseInt(request.getParameter("dutyid").toString());
		DutyDao dutyDao = new DutyDao();
		String msg = "success";
		if(dutyDao.isFull(dutyId)){
			msg = "dutyFull";
			response.getWriter().write(msg);
			dutyDao.closeCon();
			return;
		}
		SelectedDutyDao selectedDutyDao = new SelectedDutyDao();
		if(selectedDutyDao.isSelected(staffId, dutyId)){
			msg = "dutySelected";
			response.getWriter().write(msg);
			selectedDutyDao.closeCon();
			return;
		}
		SelectedDuty selectedDuty = new SelectedDuty();
		selectedDuty.setStaffId(staffId);
		selectedDuty.setDutyId(dutyId);
		if(selectedDutyDao.addSelectedDuty(selectedDuty)){
			msg = "success";
		}
		dutyDao.updateDutySelectedNum(dutyId, 1);
		dutyDao.closeCon();
		selectedDutyDao.closeCon();
		response.getWriter().write(msg);
	}
	private void getSelectedDutyList(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		int staffId = request.getParameter("staffid") == null ? 0 : Integer.parseInt(request.getParameter("staffid").toString());
		int dutyId = request.getParameter("dutyid") == null ? 0 : Integer.parseInt(request.getParameter("dutyid").toString());
		Integer currentPage = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
		Integer pageSize = request.getParameter("rows") == null ? 999 : Integer.parseInt(request.getParameter("rows"));
		SelectedDuty selectedDuty = new SelectedDuty();
		//获取当前登录用户类型
		int userType = Integer.parseInt(request.getSession().getAttribute("userType").toString());
		if(userType == 2){
			//如果是学生，只能查看自己的信息
			Staff currentUser = (Staff)request.getSession().getAttribute("user");
			staffId = currentUser.getId();
		}
		selectedDuty.setDutyId(dutyId);
		selectedDuty.setStaffId(staffId);
		SelectedDutyDao selectedDutyDao = new SelectedDutyDao();
		List<SelectedDuty> dutyList = selectedDutyDao.getSelectedDutyList(selectedDuty, new Page(currentPage, pageSize));
		int total = selectedDutyDao.getSelectedDutyListTotal(selectedDuty);
		selectedDutyDao.closeCon();
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
}
