<%--
  Created by IntelliJ IDEA.
  User: Harry Mao
  Date: 2020/12/2
  Time: 18:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
    function highLightTopMenu(menuId) {
        $("#topMenu>ul>li").removeClass("over");
        $("#" + menuId).addClass("over");
    }
</script>
<div class="top"><img src="images/portal/logo_title.gif" width="243" height="52" border="0"/></div>
<div id="topMenu" class="menu">
    <ul>
        <li id="topMenu_home"><a href="website?action=index">首页</a></li>
        <li id="topMenu_teacher"><a href="website?action=index">教师</a></li>
        <li id="topMenu_parent"><a href="website?action=index">家长</a></li>
        <li id="topMenu_student"><a href="website?action=index">学生</a></li>
        <li id="topMenu_help"><a href="website?action=index" target="_blank">帮助</a></li>
    </ul>
    <span class="question"><a href="website?action=index">
        <img src="images/portal/ico01.gif" width="16" height="16" border="0"/>问题反馈
    </a>

    </span>
</div>
