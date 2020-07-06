package servlets.group;

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


@WebServlet(name = "Group", urlPatterns = "/group")
public class GroupServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Group> allGroups = GroupService.getGroups();
            request.setAttribute("groups",allGroups);
            request.getRequestDispatcher("/views/group/index.jsp").forward(request,response);

        } catch (RuntimeException throwables) {
            throwables.printStackTrace();
            request.setAttribute("error",throwables.getMessage());
            request.getRequestDispatcher("/views/errors/error.jsp").forward(request,response);
        }



    }
}
