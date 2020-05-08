package servlets;

import exceptions.NameException;
import jdbc.StudentRepository;
import jdbc.UserRepository;
import model.Student;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "StudentServlet", urlPatterns = "/student")
public class StudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            List<Student> allStudents = StudentRepository.getAllStudents();
            request.setAttribute("student", allStudents);
            request.getRequestDispatcher("/views/student.jsp").forward(request,response);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println(throwables.getMessage());
            request.setAttribute("error", throwables.getMessage());
            request.getRequestDispatcher("/views/error.jsp");
        }

    }
}
