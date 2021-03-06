package servlets.admin;

import jdbc.StudentRepository;
import service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "DeleteStudent", urlPatterns = "/admin/teacher/deleteStudent")
public class DeleteStudent extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.valueOf(request.getParameter("id"));
        StudentService.deleteStudent(id);
        try {
            request.setAttribute("allstudents", StudentService.getAllStudents());
            request.getRequestDispatcher("/views/student/student.jsp").forward(request, response);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
