<%--
  Created by IntelliJ IDEA.
  User: Harry Mao
  Date: 2020/12/2
  Time: 18:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="css/website.css" type="text/css"/>
<script type="text/javascript" src="js/jquery-1.2.6.js"></script>
<script type="text/javascript">
    function showNotice(url,w,h){
        var iWidth,iHeight;
        iWidth=w||400;  iHeight=h||400;
        var iTop=(window.screen.availHeight-30-iHeight)/2;
        var iLeft=(window.screen.availWidth-10-iWidth)/2;
        window.open (url,'newwindow',
            'height='+iHeight+',width='+iWidth+',top='+iTop+',left='+iLeft+
            ',directories=no,toolbar=no,menubar=no,scrollbars=no,'+
            'resizable=no,location=no, status=no')

    }
</script>



