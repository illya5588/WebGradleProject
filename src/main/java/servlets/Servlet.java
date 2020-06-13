package servlets;



import jdbc.PostgresSqlConnection;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;

//TODO use JSTL (gradle)
//TODO dopost that create new group

@WebServlet(name = "Servlet",urlPatterns = "/")
public class Servlet extends HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        try {
            PostgresSqlConnection.getConnection();
            request.setAttribute("successful","Connection successful");
            request.getRequestDispatcher("/views/index.jsp").forward(request,response);
        } catch (SQLException throwables) {
            request.setAttribute("error",throwables.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/error.jsp");
            dispatcher.forward(request,response);
            //throwables.printStackTrace();

        }


    }
}
