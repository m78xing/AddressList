package com.Dao;

import com.entity.Person;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "function", value = "/function")
public class function extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String name = "";
        String gender = "";
        Integer age = 0;
        String tel = "";
        Integer func = 0;
        if(request.getParameter("name")==""
                || request.getParameter("gender")==""
                || request.getParameter("age")==""
                || request.getParameter("tel").length()!=11){
            request.getRequestDispatcher("error.jsp").forward(request,response);
        } else if (request.getParameter("func")==null) {
            func=2;
        } else {
            func = Integer.parseInt(request.getParameter("func"));
        }
        List<Person> personList=new ArrayList<Person>();
        Person person=new Person();
        person.setName(request.getParameter("name"));
        person.setGender(request.getParameter("gender"));
        person.setAge(Integer.parseInt(request.getParameter("age")));
        person.setTel(tel = request.getParameter("tel"));
        personList.add(person);

        name = request.getParameter("name");
        gender = request.getParameter("gender");
        age = Integer.parseInt(request.getParameter("age"));
        tel = request.getParameter("tel");

        String sql = "";
        BaseDao bd = new BaseDao();
        String Dis="";
        int rs = 0;
        switch (func) {
            case 1: {
                sql = "insert into user(name,gender,age,tel) values(?,?,?,?)";
                try {
                    rs= bd.modifyData(sql, new Object[]{name,gender,age,tel});
                    if (rs > 0) {
                        Dis="添加成功";
                    } else {
                        Dis="添加失败";
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            }
            case 2:{
                String Iname=request.getSession().getAttribute("iname").toString();
//                sql = "update user set name=? gender=?,age=?,tel=? where name=?";
                sql = "update user set name=?,gender=?,age=?,tel=? where name=?";
                try {
                    System.out.println("下载："+Iname);
//                    rs= bd.modifyData(sql, new Object[]{name,gender,age,tel,Iname});
                    rs= bd.modifyData(sql, new Object[]{name,gender,age,tel,Iname});
                    if (rs > 0) {
                        Dis="修改成功";
                    } else {
                        Dis="修改失败";
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Iname);
                break;
            }
            case 3:{
                sql="delete from user where name=? and gender=? and age=? and tel=?";
                try {
                    rs = bd.modifyData(sql, new Object[]{name,gender,age,tel});
                    if (rs > 0) {
                        Dis="删除成功";
                    } else {
                        Dis="删除失败";
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            }
            case 4:{
                sql="update user set gender=?,age=?,tel=? where name=?";
                try {
                    rs = bd.modifyData(sql, new Object[]{gender,age,tel,name});
                    if (rs > 0) {
                        System.out.println("修改成功");
                    } else {
                        System.out.println("修改失败");
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        request.setAttribute("personList",personList);
        request.setAttribute("Dis",Dis);
        request.getRequestDispatcher("showData.jsp").forward(request,response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }
}
