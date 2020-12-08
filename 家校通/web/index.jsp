<%--
  Created by IntelliJ IDEA.
  User: Harry Mao
  Date: 2020/12/2
  Time: 18:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>门户网站-首页</title>
    <jsp:include page="head.jsp"/>
</head>
<body>
<%--  头部开始--%>
<jsp:include page="menu_top.jsp"/>
<%--  头部结束--%>
<div class="hr01"></div>
<%--主体内容开始--%>
<div class="content">
    <div class="content_left">
        <%--        登陆信息开始--%>
        <jsp:include page="login.jsp"/>
        <%--        登陆信息结束--%>
        <%--        简单帮助开始--%>
        <jsp:include page="simpleHelp.jsp"/>
        <%--        简单帮助结束--%>
    </div>
    <div class="content_right">
        <%--        中间内容开始--%>
        <jsp:include page="home.jsp"/>
        <%--        中间内容结束--%>

        <%--        公告开始--%>
        <jsp:include page="notice.jsp"/>
        <%--        公告结束--%>

        <%--        友情链接开始--%>
        <jsp:include page="friendLink.jsp"/>
        <%--        友情链接结束--%>
    </div>
</div>
<%--  主题内容结束--%>
<%--  页脚开始--%>
<jsp:include page="bottom.jsp"/>
</body>
</html>
