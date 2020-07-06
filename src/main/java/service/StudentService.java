package service;

import exceptions.MarkException;
import exceptions.NameException;
import jdbc.GroupRepository;
import jdbc.StudentRepository;
import jdbc.UserRepository;
import model.Group;
import model.Mark;
import model.Student;
import model.Subject;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;



public class StudentService {
    protected Student student;


    public StudentService(Student student) {
        this.student = student;
    }


    //Getters and Setters
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }


    //Methods
    public static void confirmStudent(int userId) throws SQLException {
        StudentRepository.addStudentByUserId(userId);
        UserRepository.markConfirmed(userId,"student");
    }
    public static List<Student> getStudentsByCriteria(Pageable pageable, int page) throws SQLException {
        List<Student> allStudents = new ArrayList<>();

        switch (pageable.criteria) {
            case ("By surname"):
                    allStudents = StudentRepository.getPageBySurname(page,pageable.order,pageable.success);
                break;
            case ("By group"):
                allStudents = StudentRepository.getPageByGroup(page,pageable.order,pageable.success);
                break;
            case ("Default"):
                allStudents = StudentRepository.getPage(page,pageable.success);
                break;
        }
        return allStudents;
    }

    public static Integer getNumberOfPages() throws SQLException {
        return StudentRepository.numOfPages();
    }

    public static Set<Student> getStudentsBySurname() throws SQLException {
        return StudentRepository.getStudentsBySurname();

    }

    public static Set<Student> getStudentsByGroupAndSurname() throws SQLException {
        return StudentRepository.getStudentsByGroupAndSurname();

    }

    public static void addOrEditStudent(Student student, int groupId) throws NameException, SQLException {
        if (groupId != 0) {
            student.setGroup(GroupRepository.getGroupById(groupId));
            StudentRepository.editStudentGroup(student);
        }
        if (student.getStudent_ID() != 0) {
            StudentRepository.editStudent(student);
        } else {
            StudentRepository.addStudent(student);
        }


    }

    public StringBuilder subjectWithtMarkTransformation() {
        StringBuilder sb = new StringBuilder();
        for (Subject key : this.student.getMarks().keySet()) {
            Mark value = this.student.getMarks().get(key);

            sb.append("\t subject= ").append(key).append("   ").append("mark= ").append(value).append("  ").append(value.getLetterMark()).append(" ;  \n  ");
        }
        return sb;
    }

    public int age() {
        LocalDate today = LocalDate.now();
        Period result = Period.between(student.getDOB(), today);
        return result.getYears();
    }


    public void markUpdate(Subject subject, Mark mark) {

        this.student.getMarks().put(subject, mark);
        this.student.setAverageMark(countAverageMark());

    }


    public float countAverageMark() {
        float sum = 0;
        for (Mark i : this.student.getMarks().values()) {
            sum += i.getDigitMark();
        }
        return sum / this.student.getMarks().size();

    }

    public void print() {
        System.out.println("\n\n" + this.student);


    }

    public static Set<Student> addStudentToGroup(int studentId, int groupId) throws SQLException {
        Student student = StudentRepository.getStudentById(studentId).get();
        Group group = GroupRepository.getGroupById(groupId);
        GroupRepository.addStudentToGroup(student,group);
        return GroupRepository.getStudentsByGroup(group);
    }
    public static List<Student> getAllStudents() throws SQLException {
        return StudentRepository.getAllStudents();
    }

    public static Optional<Student> getStudentById(int id) throws SQLException {
       return StudentRepository.getStudentById(id);
    }

    public static void deleteStudent(int id){
        StudentRepository.deleteStudentById(id);
    }

    public static Map<Subject,Mark> getStudentMarks(int id) throws MarkException, SQLException {
        return StudentRepository.getStudentMarks(id);
    }

    public static List<Student> searchStudents(String parameter) throws SQLException {
        return StudentRepository.searchStudents(parameter);
    }
    public static int getPageNumber(String success) throws SQLException {
        return StudentRepository.getPagesBySurname(success);
    }

    public static Set<Student> getUnclassifiedStudents() throws SQLException {
        return StudentRepository.getUnclassified();
    }

}
