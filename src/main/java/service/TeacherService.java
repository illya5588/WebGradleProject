package service;

import model.Teacher;

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
}
