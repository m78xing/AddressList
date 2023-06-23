package com.servlet;

import com.Dao.PersonDao;
import com.Dao.PersonDaoImpl;
import com.entity.Person;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
//根据id找出需要被修改的人，然后传到modify.jsp中让用户填写新的信息
@WebServlet(name = "UpdatePerson", value = "/UpdatePerson")
public class UpdatePerson extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String strid=request.getParameter("id");
        int id=0;
        if(strid!=null){
            id=Integer.parseInt(strid);
        }
        PersonDao pd=new PersonDaoImpl();
        try {
            Person p=pd.getPerson(id);
            request.setAttribute("person",p);
            request.getRequestDispatcher("/modify.jsp").forward(request,response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
