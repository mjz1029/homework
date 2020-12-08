package com.mjz.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mjz.dao.AdminDao;
import com.mjz.dao.StaffDao;
import com.mjz.dao.Staff1Dao;
import com.mjz.model.Admin;
import com.mjz.model.Staff;
import com.mjz.model.Staff1;
import com.mjz.util.StringUtil;

/**
 * 
 * @author mjz
 *登录验证servlet
 */
public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5870852067427524781L;
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException{
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String method = request.getParameter("method");
		if("logout".equals(method)){
			logout(request, response);
			return;
		}
		String vcode = request.getParameter("vcode");
		String name = request.getParameter("account");
		String password = request.getParameter("password");
		int type = Integer.parseInt(request.getParameter("type"));
		String loginCpacha = request.getSession().getAttribute("loginCapcha").toString();
		if(StringUtil.isEmpty(vcode)){
			response.getWriter().write("vcodeError");
			return;
		}
		if(!vcode.toUpperCase().equals(loginCpacha.toUpperCase())){
			response.getWriter().write("vcodeError");
			return;
		}
		//验证码验证通过，对比用户名密码是否正确
		String loginStatus = "loginFaild";
		switch (type) {
			case 1:{
				AdminDao adminDao = new AdminDao();
				Admin admin = adminDao.login(name, password);
				adminDao.closeCon();
				if(admin == null){
					response.getWriter().write("loginError");
					return;
				}
				HttpSession session = request.getSession();
				session.setAttribute("user", admin);
				session.setAttribute("userType", type);
				loginStatus = "loginSuccess";
				break;
			}
			case 2:{
				StaffDao staffDao = new StaffDao();
				Staff staff = staffDao.login(name, password);
				staffDao.closeCon();
				if(staff == null){
					response.getWriter().write("loginError");
					return;
				}
				HttpSession session = request.getSession();
				session.setAttribute("user", staff);
				session.setAttribute("userType", type);
				loginStatus = "loginSuccess";
				break;
			}
			case 3:{
				Staff1Dao teahcerDao = new Staff1Dao();
				Staff1 staff1 = teahcerDao.login(name, password);
				teahcerDao.closeCon();
				if(staff1 == null){
					response.getWriter().write("loginError");
					return;
				}
				HttpSession session = request.getSession();
				session.setAttribute("user", staff1);
				session.setAttribute("userType", type);
				loginStatus = "loginSuccess";
				break;
			}
			default:
				break;
			}
		response.getWriter().write(loginStatus);
		
	}
	
	private void logout(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.getSession().removeAttribute("user");
		request.getSession().removeAttribute("userType");
		response.sendRedirect("index.jsp");
	}
}
