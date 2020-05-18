package servlets;

import exceptions.MarkException;
import jdbc.StudentRepository;
import jdbc.SubjectRepository;
import model.Mark;
import model.Student;
import model.Subject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "GetMarks",urlPatterns = "/GetMarks")
public class GetMarks extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int student_id = Integer.parseInt(request.getParameter("id"));

        try {
            request.setAttribute("id",student_id);
            request.setAttribute("student",StudentRepository.getStudentById(student_id).get());
            request.setAttribute("marks",StudentRepository.getStudentMarks(student_id));
            request.setAttribute("subjects", SubjectRepository.getAllSubjects());
            request.getRequestDispatcher("/views/mark.jsp").forward(request,response);





        } catch (SQLException | MarkException throwables) {
            throwables.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
