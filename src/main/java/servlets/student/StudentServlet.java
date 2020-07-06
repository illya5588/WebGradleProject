package servlets.student;

import exceptions.NameException;
import jdbc.GroupRepository;
import jdbc.StudentRepository;
import jdbc.UserRepository;
import model.Group;
import model.Student;
import model.User;
import service.Pageable;
import service.StudentService;

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
        doGet(request, response);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int page = 1;
        String criteria = request.getParameter("criteria");
        if (criteria == null) {
            criteria = "Default";
        }
        String order = request.getParameter("order");
        if (order == null) {
            order = "ASC";
        }

        String success = request.getParameter("success");
        if (success == null) {
            success = "All";
        }


        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        try {
            Pageable pageable = new Pageable(StudentService.getNumberOfPages(), criteria, order, success);
            request.setAttribute("currentpage", page);
            List<Student> allStudents = StudentService.getStudentsByCriteria(pageable, page);
            int size = StudentService.getPageNumber(pageable.getSuccess());
            if (size % 5 != 0) {
                pageable.setPages(size / 5 + 1);
            } else {
                pageable.setPages(size / 5);
            }

            request.setAttribute("pageable", pageable);
            request.setAttribute("allstudents", allStudents);
            request.getRequestDispatcher("/views/student/student.jsp").forward(request, response);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println(throwables.getMessage());
            request.setAttribute("error", throwables.getMessage());
            request.getRequestDispatcher("/views/errors/error.jsp");
        }

    }
}
