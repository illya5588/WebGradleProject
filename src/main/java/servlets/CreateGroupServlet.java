package servlets;

import exceptions.NameException;
import jdbc.GroupRepository;
import model.Group;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
//TODO create pages for student (List of all Students, link create student -> page(create student)
//TODO create edit Student

@WebServlet(name = "createGroup", urlPatterns = "/createGroup")
public class CreateGroupServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        String dateOfCreation = request.getParameter("date");
        String [] date = dateOfCreation.split("-");


        Group group = new Group(name,LocalDate.of(Integer.valueOf(date[2]),Integer.valueOf(date[1]),Integer.valueOf(date[0])));
        try {
            GroupRepository.addGroup(group);
            request.setAttribute("groups",GroupRepository.getAllGroups());
            request.getRequestDispatcher("/views/index.jsp").forward(request,response);
        } catch (SQLException|NameException throwables) {
            throwables.printStackTrace();
            request.setAttribute("error",throwables.getMessage());
            request.getRequestDispatcher("/views/error.jsp").forward(request,response);
        }



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


            request.getRequestDispatcher("/views/table.jsp").forward(request,response);




    }
}
