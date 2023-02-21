package com.mjz.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.mjz.dao.AttendanceDao;
import com.mjz.dao.DutyDao;
import com.mjz.dao.SelectedDutyDao;
import com.mjz.model.Attendance;
import com.mjz.model.Duty;
import com.mjz.model.Page;
import com.mjz.model.SelectedDuty;
import com.mjz.model.Staff;
import com.mjz.util.DateFormatUtil;

public class AttendanceServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException{
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String method = request.getParameter("method");
		if("toAttendanceServletListView".equals(method)){
			try {
				request.getRequestDispatcher("view/attendanceList.jsp").forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if("AddAttendance".equals(method)){
			AddAttendance(request,response);
		}else if("AttendanceList".equals(method)){
			attendanceList(request,response);
		}else if("DeleteAttendance".equals(method)){
			deleteAttendance(request,response);
		}else if("getStaffSelectedDutyList".equals(method)){
			getStaffSelectedDutyList(request, response);
		}
	}
	private void deleteAttendance(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		AttendanceDao attendanceDao = new AttendanceDao();
		String msg = "success";
		if(!attendanceDao.deleteAttendance(id)){
			msg = "error";
		}
		attendanceDao.closeCon();
		response.getWriter().write(msg);
	}
	private void attendanceList(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		int staffId = request.getParameter("staffid") == null ? 0 : Integer.parseInt(request.getParameter("staffid").toString());
		int dutyId = request.getParameter("dutyid") == null ? 0 : Integer.parseInt(request.getParameter("dutyid").toString());
		String type = request.getParameter("type");
		String date = request.getParameter("date");
		Integer currentPage = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
		Integer pageSize = request.getParameter("rows") == null ? 999 : Integer.parseInt(request.getParameter("rows"));
		Attendance attendance = new Attendance();
		//��ȡ��ǰ��¼�û�����
		int userType = Integer.parseInt(request.getSession().getAttribute("userType").toString());
		if(userType == 2){
			//�����ѧ����ֻ�ܲ鿴�Լ�����Ϣ
			Staff currentUser = (Staff)request.getSession().getAttribute("user");
			staffId = currentUser.getId();
		}
		attendance.setDutyId(dutyId);
		attendance.setStaffId(staffId);
		attendance.setDate(date);
		attendance.setType(type);
		AttendanceDao attendanceDao = new AttendanceDao();
		List<Attendance> attendanceList = attendanceDao.getSelectedDutyList(attendance, new Page(currentPage, pageSize));
		int total = attendanceDao.getAttendanceListTotal(attendance);
		attendanceDao.closeCon();
		response.setCharacterEncoding("UTF-8");
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("total", total);
		ret.put("rows", attendanceList);
		try {
			String from = request.getParameter("from");
			if("combox".equals(from)){
				response.getWriter().write(JSONArray.fromObject(attendanceList).toString());
			}else{
				response.getWriter().write(JSONObject.fromObject(ret).toString());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void AddAttendance(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		int staffId = request.getParameter("staffid") == null ? 0 : Integer.parseInt(request.getParameter("staffid").toString());
		int dutyId = request.getParameter("dutyid") == null ? 0 : Integer.parseInt(request.getParameter("dutyid").toString());
		String type = request.getParameter("type").toString();
		AttendanceDao attendanceDao = new AttendanceDao();
		Attendance attendance = new Attendance();
		attendance.setDutyId(dutyId);
		attendance.setStaffId(staffId);
		attendance.setType(type);
		attendance.setDate(DateFormatUtil.getFormatDate(new Date(), "yyyy-MM-dd"));
		String msg = "success";
		response.setCharacterEncoding("UTF-8");
		if(attendanceDao.isAttendanced(staffId, dutyId, type,DateFormatUtil.getFormatDate(new Date(), "yyyy-MM-dd"))){
			msg = "��ǩ���������ظ�ǩ����";
		}else if(!attendanceDao.addAttendance(attendance)){
			msg = "ϵͳ�ڲ���������ϵ����Ա��";
		}
		try {
			response.getWriter().write(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void getStaffSelectedDutyList(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		int staffId = request.getParameter("staff_id") == null ? 0 : Integer.parseInt(request.getParameter("staff_id").toString());
		SelectedDuty selectedDuty = new SelectedDuty();
		selectedDuty.setStaffId(staffId);
		SelectedDutyDao selectedDutyDao = new SelectedDutyDao();
		List<SelectedDuty> selectedDutyList = selectedDutyDao.getSelectedDutyList(selectedDuty, new Page(1, 999));
		selectedDutyDao.closeCon();
		String dutyId = "";
		for(SelectedDuty sc : selectedDutyList){
			dutyId += sc.getDutyId()+ ",";
		}
		dutyId = dutyId.substring(0,dutyId.length()-1);
		DutyDao dutyDao = new DutyDao();
		List<Duty> dutyList = dutyDao.getDuty(dutyId);
		dutyDao.closeCon();
		response.setCharacterEncoding("UTF-8");
		try {
			response.getWriter().write(JSONArray.fromObject(dutyList).toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
