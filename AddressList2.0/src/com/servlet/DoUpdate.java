package com.servlet;

import com.Dao.PersonDao;
import com.Dao.PersonDaoImpl;
import com.entity.Person;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

@WebServlet(name = "DoUpdate", value = "/DoUpdate")
public class DoUpdate extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String strid=request.getParameter("id");
        String name=request.getParameter("name");
        String gender=request.getParameter("gender");
        int age=Integer.parseInt(request.getParameter("age"));
        String tel=request.getParameter("tel");

        PersonDao pd=new PersonDaoImpl();
        int id=0;
        if(strid!=null && strid.equals("")){
            Person person=new Person(name,gender,age,tel);
            try {
                if(pd.insertPerson(person)==1){
                    request.getRequestDispatcher("getAllPerson").forward(request,response);
                }else{
                    request.getRequestDispatcher("/error.jsp").forward(request,response);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else{
            id=Integer.parseInt(strid);
            Person person=new Person(name,gender,age,tel,id);
            try {
                if(pd.updatePerson(person)==1){
                    System.out.println(person.getName());
                    System.out.println(person.getGender());
                    System.out.println(person.getAge());
                    System.out.println(person.getTel());
                    System.out.println(person.getId());

                    request.getRequestDispatcher("getAllPerson").forward(request,response);
                }else{
                    request.getRequestDispatcher("/error.jsp").forward(request,response);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
