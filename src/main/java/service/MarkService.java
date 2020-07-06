package service;

import exceptions.MarkException;
import jdbc.StudentRepository;
import jdbc.SubjectRepository;
import model.Mark;
import model.Subject;

import java.sql.SQLException;
import java.util.Map;

public class MarkService {
    public static Map<Subject, Mark> addMarkForStudent(int studentId, int subjectId, int mark) throws SQLException, MarkException {
        StudentRepository.addMarkForStudent(StudentRepository.getStudentById(studentId).get(), SubjectRepository.getSubjectByID(subjectId).get(),mark);
        return StudentRepository.getStudentMarks(studentId);
    }
}
