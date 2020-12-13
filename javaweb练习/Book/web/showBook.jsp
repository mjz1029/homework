<%--
  Created by IntelliJ IDEA.
  User: Harry Mao
  Date: 2020/11/17
  Time: 20:16
  To change this template use File | Settings | File Templates.
--%>
<%@page import="java.util.ArrayList" %>
<%@page import="com.book.Book" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>显示图书信息</title>
</head>
<body>
<%
    //创建一个ArrayList对象
    ArrayList book = new ArrayList();
    //创建Book对象
    Book book1 = new Book();
    book1.setBookId(001);
    book1.setBookName("西游记");
    book1.setAuthor("吴承恩");
    book1.setPress("人民文学出版社");
    book1.setPrice(47.20);
    //创建Book对象
    Book book2 = new Book();
    book2.setBookId(002);
    book2.setBookName("水浒传");
    book2.setAuthor("施耐庵");
    book2.setPress("人民文学出版社");
    book2.setPrice(63.00);
    //创建Book对象
    Book book3 = new Book();
    book3.setBookId(003);
    book3.setBookName("三国演义");
    book3.setAuthor("罗贯中");
    book3.setPress("人民文学出版社");
    book3.setPrice(48.00);
    //创建Book对象
    Book book4 = new Book();
    book4.setBookId(004);
    book4.setBookName("红楼梦");
    book4.setAuthor("曹雪芹");
    book4.setPress("人民文学出版社");
    book4.setPrice(58.04);
    //把创建的Book对象加入到ArrayList中
    book.add(book1);
    book.add(book2);
    book.add(book3);
    book.add(book4);
    //setAttribute的参数是String 和 Object
    request.setAttribute("b1", book1);
    request.setAttribute("b2", book2);
    request.setAttribute("b3", book3);
    request.setAttribute("b4", book4);
%>
<center><h1>图书信息</h1></center>
<br/>
<table align="center" border="1px" width="60%" height="150px">
    <tr>
        <th>编号</th>
        <th>书名</th>
        <th>作者</th>
        <th>出版社</th>
        <th>价格</th>
    </tr>
    <tr>
        <th>${requestScope.b1.getBookId()}</th>
        <th>${requestScope.b1.getBookName()}</th>
        <th>${requestScope.b1.getAuthor()}</th>
        <th>${requestScope.b1.getPress()}</th>
        <th>${requestScope.b1.getPrice()}</th>
    </tr>
    <tr>
        <th>${requestScope.b2.getBookId()}</th>
        <th>${requestScope.b2.getBookName()}</th>
        <th>${requestScope.b2.getAuthor()}</th>
        <th>${requestScope.b2.getPress()}</th>
        <th>${requestScope.b2.getPrice()}</th>
    </tr>
    <tr>
        <th>${requestScope.b3.getBookId()}</th>
        <th>${requestScope.b3.getBookName()}</th>
        <th>${requestScope.b3.getAuthor()}</th>
        <th>${requestScope.b3.getPress()}</th>
        <th>${requestScope.b3.getPrice()}</th>
    </tr>
    <tr>
        <th>${requestScope.b4.getBookId()}</th>
        <th>${requestScope.b4.getBookName()}</th>
        <th>${requestScope.b4.getAuthor()}</th>
        <th>${requestScope.b4.getPress()}</th>
        <th>${requestScope.b4.getPrice()}</th>
    </tr>
</table>
</body>
</html>
