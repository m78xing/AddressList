package com.servlet;

import com.Dao.PersonDao;
import com.Dao.PersonDaoImpl;
import com.entity.Person;
import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "getAllPerson", value = "/getAllPerson")
public class getAllPerson extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PersonDao pd = new PersonDaoImpl();
        try {
            List<Person> personList = pd.getAllPerson();
//            request.setAttribute("personList", personList);
//            request.getRequestDispatcher("showData.jsp").forward(request, response);

            response.setContentType("text/json;charset=utf-8");
            String json=new Gson().toJson(personList);
            response.getWriter().write(json);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
