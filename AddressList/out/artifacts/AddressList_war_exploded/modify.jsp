<%--
  Created by IntelliJ IDEA.
  User: 韩泽杰
  Date: 2023/4/1
  Time: 0:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改数据</title>
</head>
<body>
<%String path=request.getContextPath();
%>
<form action="<%=path%>/function">
  <%
    Object iname=request.getAttribute("iname");
    out.println(iname.toString());
    session.setAttribute("iname",iname);
  %>
  请输入修改后的数据<br/>
  姓名：<input type="text" name="name">
  性别：<input type="radio" name="gender" value="男">男
  <input type="radio" name="gender" value="女">女
  年龄：<input type="number" name="age">
  电话号码：<input type="tel" name="tel">
  <input type="submit" value="提交">
</form>
</body>
</html>
