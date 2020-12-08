<%--
  Created by IntelliJ IDEA.
  User: Harry Mao
  Date: 2020/11/24
  Time: 15:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>错误页面</title>
</head>
<body>
<h1>出错了</h1>
<p>
    <%
        request.setCharacterEncoding("utf-8");
    %>
    <%=request.getParameter("error")%>
</p>
<p>
    <a href="register.jsp">重新注册</a>
</p>
<p>
    <a href="login.jsp">返回登录</a>
</p>
</body>
</html>
