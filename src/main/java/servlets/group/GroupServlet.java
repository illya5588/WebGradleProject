package servlets.group;

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


@WebServlet(name = "Group", urlPatterns = "/group")
public class GroupServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
//            List<GroupDTO> allGroups = GroupService.getAllGroups();
            List<Group> allGroups = GroupRepository.getAllGroups();
            request.setAttribute("groups",allGroups);
            request.getRequestDispatcher("/views/index.jpg").forward(request,response);
            //request.getRequestDispatcher("/views/student.jpg").forward(request,response);

        } catch (RuntimeException throwables) {
            throwables.printStackTrace();

            request.setAttribute("error",throwables.getMessage());
            request.getRequestDispatcher("/views/error.jsp").forward(request,response);
        }



    }
}
