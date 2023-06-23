
<%--
  Created by IntelliJ IDEA.
  User: 韩泽杰
  Date: 2023/4/4
  Time: 21:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="appearance.css">
</head>
<center>
<body>
<%
    String path=request.getContextPath();
    request.getAttribute("person");
%>
<form>
    <table>
        <tr>
            <th>姓名：<input id="name" type="text" name="name" value="${person.name}"></th>
        </tr>
        <tr>
            <th>性别：<input type="radio" name="gender" value="男">男
                <input type="radio" name="gender" value="女">女<br/></th>
        </tr>
        <tr>
            <th>年龄：<input id="age" type="number" name="age" value="${person.age}"></th>
        </tr>
        <tr>
            <th>电话号码：<input id="tel" type="tel" name="tel" value="${person.tel}"></th>
        </tr>
    </table>
    <input id="id" type="hidden" name="id" readonly="readonly" value="${person.id}">
    <input type="button" value="确定" onclick="CheckAndUpdate()">
</form>
<script>
    function CheckAndUpdate(){
        const name=document.getElementById("name").value;
        const id=document.getElementById("id").value;
        const gender=document.getElementsByName("gender");
        const age=document.getElementById("age").value;
        const tel = document.getElementById("tel").value;

        let sGender=gender[0].checked?gender[0].value:gender[1].value;

        if(tel.length!==11||isNaN(tel)){
            alert("请输入11位电话号码");
        }else if (name===""){
            alert("请输入姓名");
        }else if (age==="" || age<=0 || age>200){
            alert("请输入正确的年龄");
        }else if(!(gender[1].checked||gender[0].checked)){
            alert("请选择性别");
        } else{
            const xhttp=new XMLHttpRequest();
            let url="http://localhost:8080/AddressList2_0_Web_exploded/DoUpdate?name="+name+"&gender="+sGender+"&age="+age+"&tel="+tel+"&id="+id;
            xhttp.open("GET",url,true);
            xhttp.send();
            xhttp.onreadystatechange=function () {
                if(this.readyState===4 && this.status===200){
                    window.location.assign("http://localhost:8080/AddressList2_0_Web_exploded/showData.jsp");
                }
            }
        }
    }
</script>
<script src="func.js"></script>
</body>
</center>
</html>

