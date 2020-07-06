package servlets.group;

import exceptions.NameException;
import jdbc.GroupRepository;
import jdbc.StudentRepository;
import model.Group;
import service.GroupService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "GroupStudents", urlPatterns = "/groupstudents")
public class GroupStudents extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.valueOf(request.getParameter("id"));
        Group group = null;


        try {
            group = GroupService.getGroupById(id);

        } catch (SQLException | NameException throwables) {
            throwables.printStackTrace();
        }
        try {
            request.setAttribute("groupId",id);
            request.setAttribute("name",group.getName());
            request.setAttribute("groupstudents",GroupService.getStudentsByGroup(group));
            request.getRequestDispatcher("/views/group/groupstudents.jsp").forward(request,response);
        } catch (SQLException | NameException throwables) {
            throwables.printStackTrace();
        }
    }
}
