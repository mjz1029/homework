<%@ page import="com.example.messageboard.javabean.User" %><%--
  Created by IntelliJ IDEA.
  User: Harry Mao
  Date: 2020/11/24
  Time: 15:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加留言</title>
</head>
<body>
<h1>请添加留言</h1>
<p>欢迎您：
        <%=((User)session.getAttribute("user")).getUserid()%>
    <form action="AddMessage" method="post">
<p>留言标题：</p>
<p><input type="text" name="title"/></p>
<p>留言内容：</p>
<p><textarea rows="5" clos="20" name="content"></textarea></p>
<p><input type="submit" value="添加留言"/></p>
</form>
</p>
</body>
</html>
