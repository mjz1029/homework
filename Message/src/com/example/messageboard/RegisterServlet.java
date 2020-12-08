/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.messageboard;

import java.io.IOException;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "RegisterServlet", urlPatterns = {"/Register"})
public class RegisterServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //1.获取输入
        request.setCharacterEncoding("utf-8");
        String userid, password1, password2;
        String errorMessage="";
        userid = request.getParameter("userid");
        password1 = request.getParameter("password1");
        password2 = request.getParameter("password2");

        //执行业务逻辑并转发视图
        DB db = new DB();
        if(!password1.equals(password2)){
            errorMessage = URLEncoder.encode("两次输入的密码不同","utf-8");
            response.sendRedirect("error.jsp?error=" + errorMessage);
        }
        else if(db.queryUser(userid)!=null){
            errorMessage = URLEncoder.encode("用户名已经存在","utf-8");
            response.sendRedirect("error.jsp?error=" + errorMessage);
        }
        else if(db.insertUser(userid, password1))
            response.sendRedirect("login.jsp");
        else{
            errorMessage = URLEncoder.encode("注册用户出错","utf-8");
            response.sendRedirect("error.jsp?error=" + errorMessage);
        }


    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
