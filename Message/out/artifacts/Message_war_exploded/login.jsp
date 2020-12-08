<%--
  Created by IntelliJ IDEA.
  User: Harry Mao
  Date: 2020/11/24
  Time: 15:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户登录</title>
</head>
<body>
<h1>用户请登录</h1>
<form action="Login" method="post">
    <p>用户名：<input type="text" name="userid"/> </p>
    <p>密码：<input type="password" name="password"/> </p>
    <p><input type="submit" value="登录"/>&nbsp;
        <input type="reset" value="重填"/> </p>
</form>
<p>
    <a href="register.jsp">用户注册</a>
</p>
</body>
</html>
