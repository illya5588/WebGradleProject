package service;

import jdbc.SubjectRepository;
import model.Subject;

import java.sql.SQLException;
import java.util.List;

public class SubjectService {
    public static List<Subject> getAllSubjects() throws SQLException {
        return SubjectRepository.getAllSubjects();
    }
}
