package servlets.admin;

import jdbc.StudentRepository;
import jdbc.TeacherRepository;
import jdbc.UserRepository;
import model.Student;
import model.Teacher;
import model.User;
import service.ConfirmationService;
import service.StudentService;
import service.TeacherService;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

@WebServlet(name = "ConfirmUsers", urlPatterns = "/admin/ConfirmUsers")
public class ConfirmUsers extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String role = request.getParameter("role");
        int id  = Integer.parseInt(request.getParameter("id"));

        try {
            request.setAttribute("allusers", UserService.confirmUser(id,role));
            request.getRequestDispatcher("/views/confirmation.jsp").forward(request,response);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setAttribute("allusers", UserService.getUnconfirmedUsers());
            request.getRequestDispatcher("/views/utils/confirmation.jsp").forward(request,response);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
