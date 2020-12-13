<%@ page import="com.example.messageboard.javabean.Message" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.messageboard.javabean.User" %><%--
  Created by IntelliJ IDEA.
  User: Harry Mao
  Date: 2020/11/24
  Time: 15:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>留言板</title>
</head>
<body>
<h1>欢迎使用留言板</h1>
<p>欢迎你：
    <%
        User user1 = (User)session.getAttribute("user");
        out.print(user1.getUserid());
    %>
    <%=((User)session.getAttribute("user")).getUserid()%>
    <jsp:useBean id="user"
                 class="com.example.messageboard.javabean.User"
                 scope="session" />
    <jsp:getProperty name="user"
                     property="userid" />
</p>
<p>

</p>
<table border="1">
    <tr>
        <td>留言标题</td>
        <td>留言内容</td>
        <td>留言人</td>
    </tr>
    <%
        ArrayList<Message> messageList =
                (ArrayList<Message>)request.getAttribute("messages");
        Message m = null;
        for(int i = 0; i < messageList.size(); i++){
            m = messageList.get(i);
    %>
    <tr>
        <td><%=m.getTitle()%></td>
        <td><%=m.getContent()%></td>
        <td><%=m.getUserid()%></td>
    </tr>
    <%}%>
</table>
<p><a href="addmessage.jsp">添加新的留言</a></p>
</body>
</html>
