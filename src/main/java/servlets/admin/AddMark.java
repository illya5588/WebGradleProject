package servlets.admin;

import exceptions.MarkException;
import jdbc.GroupRepository;
import jdbc.StudentRepository;
import jdbc.SubjectRepository;
import model.Mark;
import model.Subject;
import model.SubjectType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AddMark", urlPatterns = "/admin/AddMark")
public class AddMark extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int subject_id  = Integer.parseInt(request.getParameter("subjectSelect"));
        int student_id = Integer.parseInt(request.getParameter("studentSelect"));
        int mark = Integer.parseInt(request.getParameter("mark"));
        try {
            StudentRepository.addMarkForStudent(StudentRepository.getStudentById(student_id).get(),SubjectRepository.getSubjectByID(subject_id).get(),mark);
            request.setAttribute("marks",StudentRepository.getStudentMarks(student_id));

            request.getRequestDispatcher("/views/mark.jsp").forward(request,response);
        } catch (SQLException | MarkException throwables) {
            throwables.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setAttribute("subjects", SubjectRepository.getAllSubjects());
            request.setAttribute("students", StudentRepository.getAllStudents());
            request.getRequestDispatcher("/views/addmark.jsp").forward(request,response);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}