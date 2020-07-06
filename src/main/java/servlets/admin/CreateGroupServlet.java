package servlets.admin;

import exceptions.DateException;
import exceptions.NameException;
import jdbc.GroupRepository;
import model.Group;
import service.GroupService;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;


@WebServlet(name = "createGroup", urlPatterns = "/admin/createGroup")
public class CreateGroupServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        String dateOfCreation = request.getParameter("date");

        try {
            GroupService.addGroup(name, dateOfCreation);
            request.setAttribute("groups", GroupService.getGroups());
            request.getRequestDispatcher("/views/group/index.jsp").forward(request, response);
        } catch (SQLException | NameException | DateException throwables) {
            throwables.printStackTrace();
            request.setAttribute("error", throwables.getMessage());
            request.getRequestDispatcher("/views/errors/error.jsp").forward(request, response);
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/views/group/table.jsp").forward(request, response);

    }
}
