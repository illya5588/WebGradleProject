package servlets.admin;

import service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet(name = "AddStudentToGroup", urlPatterns = "/admin/addStudent")
public class AddStudentToGroup extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    int studentId = Integer.parseInt(request.getParameter("studentSelect"));
    int groupId = Integer.parseInt(request.getParameter("groupId"));
        try {
            request.setAttribute("groupstudents", StudentService.addStudentToGroup(studentId,groupId));
            request.getRequestDispatcher("/views/group/groupstudents.jsp").forward(request,response);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int groupId = Integer.parseInt(request.getParameter("id"));
            request.setAttribute("groupID",groupId);
            request.setAttribute("students",StudentService.getAllStudents());
            request.getRequestDispatcher("/views/student/addstudenttogroup.jsp").forward(request,response);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
