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

@WebServlet(name = "getPersonFuzzy", value = "/getPersonFuzzy")
public class getPersonFuzzy extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        PersonDao pd=new PersonDaoImpl();
        String fuzzyName=request.getParameter("name");

        if(fuzzyName!=null && !fuzzyName.equals("")){
            try {
                List<Person> list=pd.getPersonFuzzy(fuzzyName);
                response.setContentType("text/json;charset=utf-8");
                String json=new Gson().toJson(list);
                response.getWriter().write(json);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else{
            request.getRequestDispatcher("/error.jsp").forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
