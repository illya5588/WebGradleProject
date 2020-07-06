package servlets.utils;

import jdbc.StudentRepository;
import jdbc.TeacherRepository;
import jdbc.UserRepository;
import model.Manager;
import model.Student;
import model.Teacher;
import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

@WebServlet(name = "Register", urlPatterns = "/Register")
public class Register extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String role = request.getParameter("role");
        String surname = request.getParameter("surname");
        String name = request.getParameter("name");
        String dateOfBirth = request.getParameter("date");
        String[] date = dateOfBirth.split("-");
        String password = request.getParameter("password");
        LocalDate birth = LocalDate.of(Integer.valueOf(date[0]), Integer.valueOf(date[1]), Integer.valueOf(date[2]));
        try {

            User user = new User();
            String login = request.getParameter("login");
            user.setRole(role);
            user.setLogin(login.toLowerCase());
            user.setPassword(password);
            user.setSurname(surname);
            user.setName(name);
            user.setDOB(birth);
            UserService.registerUser(user);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        request.getRequestDispatcher("/views/utils/register.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/views/utils/register.jsp").forward(request, response);
    }
}
