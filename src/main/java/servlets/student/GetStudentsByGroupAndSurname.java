package servlets.student;

import model.Student;
import service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Set;

@WebServlet(name = "GetStudentsByGroupAndSurname", urlPatterns = "/GetStudentsByGroupAndSurname")
public class GetStudentsByGroupAndSurname extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Set<Student> allStudents = StudentService.getStudentsByGroupAndSurname();
            request.setAttribute("allstudents", allStudents);
            request.getRequestDispatcher("/views/student.jsp").forward(request,response);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println(throwables.getMessage());
            request.setAttribute("error", throwables.getMessage());
            request.getRequestDispatcher("/views/error.jsp");
        }
    }
}
