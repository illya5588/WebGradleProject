package service;

import jdbc.TeacherRepository;
import jdbc.UserRepository;
import model.Teacher;

import java.sql.SQLException;

public class TeacherService {
    protected Teacher teacher;

    public TeacherService(Teacher teacher) {
        this.teacher = teacher;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String makeFullName() {
        StringBuilder sb = new StringBuilder(teacher.getSurname());
        sb.append(" ").append(teacher.getName().charAt(0)).append(".");


        return sb.toString();
    }
    public static void confirmTeacher(int id) throws SQLException {
        TeacherRepository.addTeacherByUserId(id);
        UserRepository.markConfirmed(id,"teacher");
    }
}
