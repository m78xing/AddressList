<%@ page import="com.Dao.PersonDao" %>
<%@ page import="com.entity.Person" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: 韩泽杰
  Date: 2023/4/3
  Time: 21:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>主页面</title>
    <link rel="stylesheet" href="appearance.css">
</head>
    <body>
    <div align="center">
        <input type="button" value="添加" onclick=location.href="modify.jsp"><br/>
        <form>
            <input id="fuzzyName" type="text" name="fuzzyName">
<%--            <input type="submit" value="查询">--%>
            <input type="button" value="查询" onclick="fuzzySelect()">

        </form>
    </div>
    <hr/><%--  添加横线   --%>

    <table>
        <thead>
            <tr>
                <th>姓名</th>
                <th>性别</th>
                <th>年龄</th>
                <th>电话</th>
            </tr>
        </thead>
    </table>
    <script>
        const xhttp=new XMLHttpRequest();
        let url="http://localhost:8080/AddressList2_0_Web_exploded//getAllPerson";
        xhttp.open("GET",url,true);
        xhttp.send();
        xhttp.onreadystatechange=function () {
            if(this.readyState===4 && this.status===200){
                let result=this.responseText;
                let persons=eval('('+result+')');
                //console.log(persons);
                showAll(persons);
            }
        }
        function fuzzySelect() {
            const fName = document.getElementById("fuzzyName").value;
            if(fName!=null && fName!==""){
                const xhttp = new XMLHttpRequest();
                let url="http://localhost:8080/AddressList2_0_Web_exploded/getPersonFuzzy?name="+fName;

                xhttp.open("GET",url,true);
                xhttp.send();
                xhttp.onreadystatechange = function() {
                    if (this.readyState === 4 && this.status === 200) {
                        let result=this.responseText;
                        let persons=eval('('+result+')');
                        showAll(persons);
                    }
                }
            }else{
                alert("请输入想要查询者的姓名或关键字");
            }
        }
    </script>
    <script src="func.js"></script>
    </body>
</html>
