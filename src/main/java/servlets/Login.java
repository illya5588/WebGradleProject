package servlets;

import jdbc.PasswordUtility;
import jdbc.UserRepository;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "Login", urlPatterns = "/Login")
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        User user = new User();

        try {
            user = UserRepository.getUserByLoginAndPassword(login, password);
            HttpSession session = request.getSession();
            String role = user.getRole();
            session.setAttribute("role", role);
            session.setAttribute("name", user.getName());
            session.setAttribute("surname", user.getSurname());

            if("admin".equals(role)){
                request.setAttribute("confirmed",UserRepository.getUnconfirmedUsers().size());
            }
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/views/login.jsp").forward(request, response);
    }
}
