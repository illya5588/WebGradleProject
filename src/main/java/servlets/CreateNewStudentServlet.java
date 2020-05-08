package servlets;

import exceptions.NameException;
import jdbc.StudentRepository;
import model.Group;
import model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

@WebServlet(name = "newStudent", urlPatterns = "/newStudent")
public class CreateNewStudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dateOfBirth = request.getParameter("date");
        String [] date = dateOfBirth.split("-");

        Student student = new Student(request.getParameter("surname"),request.getParameter("name"),LocalDate.of(Integer.valueOf(date[2]),Integer.valueOf(date[1]),Integer.valueOf(date[0])));

        try {
            StudentRepository.addStudent(student);
            request.setAttribute("students",StudentRepository.getAllStudents());
            request.getRequestDispatcher("/views/student.jsp");
        } catch (SQLException | NameException throwables) {
            throwables.printStackTrace();
            request.setAttribute("error",throwables.getMessage());
            request.getRequestDispatcher("/views/error.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/addstudent.jsp").forward(request,response);
    }
}
