package com.example.messageboard;

import com.example.messageboard.javabean.Message;
import com.example.messageboard.javabean.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddMessageServlet",urlPatterns = {"/AddMessage"})
public class AddMessageServlet extends HttpServlet {
    protected void  processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException    {
        //获取用户用户输入
        request.setCharacterEncoding("utf-8");
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        User user = (User)(request.getSession().getAttribute("user"));
        String userid = user.getUserid();

        //调用模型
        Message message = new Message();
        message.setTitle(title);
        message.setContent(content);
        message.setUserid(userid);
        boolean result = addMessage(message);

        //转发视图
        if(result)
            response.sendRedirect("ShowMessage");
        else
            response.sendRedirect("addMessage.jsps");

    }

    private boolean addMessage(Message message){
        DB db = new DB();
        return db.insertMessage(message.getTitle(),
                message.getContent(),
                message.getUserid());
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
