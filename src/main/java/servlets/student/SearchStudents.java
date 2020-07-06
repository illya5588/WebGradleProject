package servlets.student;

import jdbc.GroupRepository;
import jdbc.StudentRepository;
import service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "SearchStudents", urlPatterns = "/SearchStudents")
public class SearchStudents extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String search = request.getParameter("search");
        try {
            request.setAttribute("allsearched", StudentService.searchStudents(search));
            request.getRequestDispatcher("/views/student/searchedstudents.jsp").forward(request,response);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
