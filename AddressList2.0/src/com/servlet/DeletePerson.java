package com.servlet;

import com.Dao.PersonDao;
import com.Dao.PersonDaoImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "DeletePerson", value = "/DeletePerson")
public class DeletePerson extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String strid=request.getParameter("id");
        System.out.println(strid);
        Integer id=0;
        if(strid!=null){
            id=Integer.parseInt(strid);
        }
        PersonDao pd=new PersonDaoImpl();
        try {
            if(pd.delPerson(id)==1){
                request.getRequestDispatcher("getAllPerson").forward(request,response);
            }else{
                request.getRequestDispatcher("/error.jsp").forward(request,response);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
