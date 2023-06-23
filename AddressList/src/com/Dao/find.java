package com.Dao;

import com.entity.Person;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "find", value = "/find")
public class find extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        String sql = "";
        BaseDao bd = new BaseDao();
        sql = "select * from user where name=?";
        ResultSet rs = null;
        try {
            rs = bd.getDataByAny(sql, new Object[]{name});
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
//        request.setAttribute("data",rs);
        Integer func=Integer.parseInt(request.getParameter("func"));
        if(func==2)
        {
            try {
                if(rs.next()){
                    System.out.println(rs.getString(1));
                    request.setAttribute("iname",rs.getString(1));
                }
                else{
                    System.out.println("无结果");
                    request.getRequestDispatcher("error.jsp").forward(request,response);
                }
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            request.getRequestDispatcher("modify.jsp").forward(request,response);
        }
        else{
                try {
                    String Dis="无符合条件的用户";
                    List<Person> personList=new ArrayList<Person>();
                    Person person=null;
                    while (rs.next()) {
                        person=new Person();
                        person.setName(rs.getString(1));
                        person.setGender(rs.getString(2));
                        person.setAge(Integer.parseInt(rs.getString(3)));
                        person.setTel(rs.getString(4));
                        personList.add(person);
                        Dis="查询成功";
                    }
                    request.setAttribute("Dis",Dis);
                    request.setAttribute("personList",personList);
                    request.getRequestDispatcher("showData.jsp").forward(request,response);
                    rs.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }
}
