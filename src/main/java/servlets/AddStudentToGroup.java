package servlets;

import exceptions.NameException;
import jdbc.GroupRepository;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "AddStudentToGroup", urlPatterns = "/addstudenttogroup")
public class AddStudentToGroup extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int groupsSelect = Integer.valueOf(request.getParameter("groupsSelect"));
        int studentId = Integer.valueOf(request.getParameter("student_id"));
        Group group = new Group();
       Student student = null;

        try {
            group = GroupRepository.getGroupById(groupsSelect);
            student = StudentRepository.getStudentById(studentId).get();
        } catch (SQLException | NameException throwables) {
            throwables.printStackTrace();
        }
        student.setGroup(group);
        StudentRepository.editStudentGroup(student);
        List<Student> allStudents = new ArrayList<>();
        try {
            allStudents = StudentRepository.getAllStudents();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        request.setAttribute("groupchangestudents",allStudents);
        request.getRequestDispatcher("/views/student.jsp").forward(request,response);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int groupsSelect = Integer.valueOf(request.getParameter("groupsSelect"));
        int studentId = Integer.valueOf(request.getParameter("id"));
    }
}
