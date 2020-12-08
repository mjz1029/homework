<%--
  Created by IntelliJ IDEA.
  User: Harry Mao
  Date: 2020/11/18
  Time: 14:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>登录界面</title>
</head>
<body>
<form action="LoginServlet" method="post">
    账号：<input type="text" name="userid"/>
    <br>
    密码：<input type="password" name="password"/>
    <br>
    <input type="submit" value="登录"/>
</form>
</body>
</html>

