package servlets.admin;

import exceptions.NameException;
import model.Group;
import model.Student;
import service.GroupService;
import service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "newStudent", urlPatterns = "/admin/newStudent")
public class CreateNewStudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String dateOfBirth = request.getParameter("date");
        String[] date = dateOfBirth.split("-");
        String groupID = request.getParameter("groupsSelect");
        Student student = null;
        try {
            student = new Student(request.getParameter("surname"), request.getParameter("name"), LocalDate.of(Integer.valueOf(date[0]), Integer.valueOf(date[1]), Integer.valueOf(date[2])));
            if (!("").equals(request.getParameter("id"))) {
                student.setStudent_ID(Integer.parseInt(request.getParameter("id")));
            }
        } catch (NameException e) {
            errorRedirect(e, request, response);
            return;
        }

        try {
            StudentService.addOrEditStudent(student, Integer.valueOf(groupID));
            request.setAttribute("allstudents", StudentService.getAllStudents());
            request.getRequestDispatcher("/views/student/student.jsp").forward(request, response);
        } catch (SQLException e) {
            errorRedirect(e, request, response);
        }
    }

    private void errorRedirect(Exception e, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        e.printStackTrace();
        request.setAttribute("error", e.getMessage());
        request.getRequestDispatcher("/views/errors/error.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Group> allGroups = new ArrayList<>();
        allGroups = GroupService.getGroups();
        if (request.getParameter("id") != null) {
            int id = Integer.valueOf(request.getParameter("id"));
            Optional<Student> student = null;
            try {
                student = StudentService.getStudentById(id);
            } catch (SQLException throwables) {
                request.setAttribute("error", "Student is not present");
                request.getRequestDispatcher("/views/errors/error.jsp");
                throwables.printStackTrace();
            }
            if (student.isPresent()) {
                request.setAttribute("groups", allGroups);
                request.setAttribute("student", student.get());
                request.setAttribute("group", student.get().getGroup());
                request.getRequestDispatcher("/views/student/addstudent.jsp").forward(request, response);

            } else {
                request.setAttribute("error", "Student is not present");
                request.getRequestDispatcher("/views/errors/error.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("groups", allGroups);
            request.getRequestDispatcher("/views/student/addstudent.jsp").forward(request, response);
        }

    }
}
