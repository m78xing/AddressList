<%@ page import="java.util.ArrayList" %>
<%@ page import="com.entity.Person" %><%--
  Created by IntelliJ IDEA.
  User: 韩泽杰
  Date: 2023/3/31
  Time: 23:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>处理结果</title>
</head>
<body>
<h2 align="center"><%out.print(request.getAttribute("Dis").toString());%></h2>
<table align="center">
    <tr>
        <th>序号</th>
        <th>姓名</th>
        <th>密码</th>
        <th>操作</th>
    </tr>
    <%ArrayList personList=(ArrayList)request.getAttribute("personList");
    for(int i=0;i<personList.size();i++){
    Person person=(Person)personList.get(i);%>
    <tr><td><%=person.getName() %></td>
        <td><%=person.getGender() %></td>
        <td><%=person.getAge() %></td>
        <td><%=person.getTel() %></td>
<%--        <th><a href="/prj-servlet-22/modify.jsp">修改</a><a href="/prj-servlet-22/delete.jsp">删除</a></th>--%>
            <%
        } %>
</table>
</body>
</html>
