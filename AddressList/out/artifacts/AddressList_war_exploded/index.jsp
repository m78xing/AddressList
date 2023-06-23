<%--
  Created by IntelliJ IDEA.
  User: 韩泽杰
  Date: 2023/3/31
  Time: 22:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
<%--  <%=path%>/add--%>
  <%String path=request.getContextPath();
  %>
  <form action="<%=path%>/function" method="post">
    姓名：<input type="text" name="name">
    性别：<input type="radio" name="gender" value="男">男
    <input type="radio" name="gender" value="女">女
    年龄：<input type="number" name="age">
    电话号码：<input type="tel" name="tel">
    <select name="func">
      <option value="1">添加</option>
      <option value="3">删除</option>
      <option value="4">修改</option>
    </select>
    <input type="submit" value="提交">
  </form>
<form action="<%=path%>/find" method="get">
  查询：输入姓名：<input type="text" name="name">
  <select name="func">
    <option value="1">查询</option>
    <option value="2">修改</option>
  </select>
  <input type="submit" value="提交">
</form>
  </body>
</html>
