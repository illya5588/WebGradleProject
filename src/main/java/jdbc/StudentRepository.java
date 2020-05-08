package jdbc;

import exceptions.NameException;
import exceptions.UniqueException;
import model.Mark;
import model.Student;
import model.Subject;
import model.User;
import org.postgresql.util.PSQLException;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class StudentRepository {

 private static String ADD_NEW_STUDENT = "INSERT INTO students (student_uuid,user_id,created_on,group_id)"
            + "values(?,?,?,?)";
 private static String ADD_MARKS = "INSERT INTO marks (student_id,subject_id,mark,created_on)"
            + "values(?,?,?,?)";



    public static Student addStudent(Student student) throws SQLException, NameException {
        if (UserRepository.isUserPresent(student)) {
            if (isStudentPresent(student)) {
                throw new UniqueException("This student is already in the table!!!");
            } else {
                if(!GroupRepository.isGroupPresent(student.getGroup())){
                   GroupRepository.addGroup(student.getGroup());
                }
               executePreparedStatementToAddStudent(student);
                return student;
            }
        }
            User user = UserRepository.addUser(student);
            executePreparedStatementToAddStudent(student);
            return student ;
    }

    private static void executePreparedStatementToAddStudent(Student student) throws SQLException, NameException {

        try (Connection connection = PostgresSqlConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_NEW_STUDENT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, student.getStudent_uuid());
            preparedStatement.setInt(2, UserRepository.getUserIDByFullInformation(student));
            preparedStatement.setObject(3, LocalDateTime.now());
            preparedStatement.setInt(4, GroupRepository.getGroupId(student.getGroup()));
            preparedStatement.executeUpdate();

            System.out.println("Student is successfully added!");
        }
    }


    public static void addMark(Student student) throws SQLException, NameException {
        Map<Subject, Mark> buf = student.getMarks();
        for(Subject subject:buf.keySet()){
            if(SubjectRepository.isSubjectPresent(subject)){
                if(isStudentMarkPresent(student,subject)){
                   System.err.println("Student's Mark "+buf.get(subject).getDigitMark()+" in subject "+subject.getName()+" is already in the table");
                }
                else {


                    try (Connection connection = PostgresSqlConnection.getConnection()) {
                        PreparedStatement preparedStatement = connection.prepareStatement(ADD_MARKS, Statement.RETURN_GENERATED_KEYS);

                        preparedStatement.setInt(1, StudentRepository.getStudentByFullInformation(student));
                        preparedStatement.setInt(2, SubjectRepository.getSubjectID(subject));
                        preparedStatement.setInt(3, buf.get(subject).getDigitMark());
                        preparedStatement.setObject(4, LocalDateTime.now());

                        preparedStatement.executeUpdate();
                    } catch (PSQLException e){
                        throw new UniqueException("This student mark is already exists!!!");
                    }
               }
            } else{
                SubjectRepository.addSubject(subject);


                try (Connection connection = PostgresSqlConnection.getConnection()) {
                    PreparedStatement preparedStatement = connection.prepareStatement(ADD_MARKS,Statement.RETURN_GENERATED_KEYS);

                    preparedStatement.setInt(1, StudentRepository.getStudentByFullInformation(student));
                    preparedStatement.setInt(2, SubjectRepository.getSubjectID(subject));
                    preparedStatement.setInt(3, buf.get(subject).getDigitMark());
                    preparedStatement.setObject(4,LocalDateTime.now());

                    preparedStatement.executeUpdate();
                }
            }
        }

    }

    public static void addMarkForStudent(Student student, Subject subject, int mark) throws SQLException, NameException {
        if(getStudentByFullInformation(student)==0|| SubjectRepository.getSubjectID(subject)==0){
            throw new IllegalArgumentException("This student or subject does not exist");
        }
        String ADD_MARKS = "INSERT INTO marks (student_id,subject_id,mark,created_on)"
                + "values(?,?,?,?)";

        try (Connection connection = PostgresSqlConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_MARKS);

            preparedStatement.setInt(1,student.getStudent_ID());
            preparedStatement.setInt(2, subject.getID());
            preparedStatement.setInt(3, mark);
            preparedStatement.setObject(4,LocalDateTime.now());

            preparedStatement.executeUpdate();
        }
    }
    public static void addMarksForStudent(Student student, Map<Subject,Integer> marks) throws SQLException, NameException {
        for (Subject subject:marks.keySet()){
            addMarkForStudent(student,subject,marks.get(subject));
        }
    }
    public static void createStudentTable() {
        String sql = "CREATE TABLE IF NOT EXISTS students" +
                "( student_id serial PRIMARY KEY, " +
                " student_uuid UUID NOT NULL, " +
                "user_id INTEGER REFERENCES users(user_id), "+
                "group_id INTEGER REFERENCES groups(group_id) , created_on TIMESTAMP NOT NULL, updated_at TIMESTAMP)";

        try (Connection connection = PostgresSqlConnection.getConnection();
             Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(sql);

            System.out.println("New table successfully created! ");

        } catch (SQLException e) {
            System.out.println("Something wrong!!! Table is not created!");
            e.getStackTrace();
        }
    }
    public static void createMarksTable() {
        String sql = "CREATE TABLE IF NOT EXISTS marks" +
                "( mark_id serial PRIMARY KEY, " +
                " student_id INTEGER REFERENCES students(student_id), " +
                "subject_id INTEGER REFERENCES subject(subject_id), "+
                "mark INTEGER NOT NULL,"+
                " created_on TIMESTAMP NOT NULL, updated_at TIMESTAMP, Unique(student_id,subject_id,mark))";

        try (Connection connection = PostgresSqlConnection.getConnection();
             Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("New table successfully created! ");

        } catch (SQLException e) {
            System.out.println("Something wrong!!! Table is not created!");
            e.getStackTrace();
        }
    }

    public static void deleteStudentTable() {
        String sql = "DROP TABLE students";
        try (Connection connection = PostgresSqlConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.execute();
            System.out.println("Table is successfully deleted! ");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public static void deleteMarksTable() {
        String sql = "DROP TABLE marks";
        try (Connection connection = PostgresSqlConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.execute();
            System.out.println("Table is successfully deleted! ");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static boolean isStudentPresent(Student student) throws SQLException {

        String sql = "select student_id from students join users on (users.user_id = students.user_id) where users.surname = '"+student.getSurname()+"' AND users.name = '"+student.getName()+"' AND users.dateofbirth = '"+student.getDOB()+"'";
        Connection connection = PostgresSqlConnection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);

        while (rs.next()) {
            return true;
        }

        return false;
    }

    public static int getStudentByFullInformation(Student student) throws SQLException, NameException {
        //User user  = student.getUser();
       // UserRepository.getUserIDByFullInformation(user);
        String sql = "select student_id from students join users on (users.user_id = students.user_id) where users.surname = '"+student.getSurname()+"' AND users.name = '"+student.getName()+"' AND users.dateofbirth = '"+student.getDOB()+"'";
        try (Connection connection = PostgresSqlConnection.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            rs.next();
            return rs.getInt("student_id");
        }
    }

    public static boolean isStudentMarkPresent(Student student, Subject subject) throws SQLException, NameException {

        String sql = " select*from marks where student_id = '"+ StudentRepository.getStudentByFullInformation(student)+ "' and subject_id = ' "+ SubjectRepository.getSubjectID(subject)+"' and mark = ' "+student.getMarks().get(subject).getDigitMark()+" '";
        Connection connection = PostgresSqlConnection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);

        while (rs.next()) {
            return true;
        }
        return false;
    }

    public static List<Student> getAllStudents() throws SQLException {
        List<Student> allStudents = new ArrayList<>();
        String GET_ALL_STUDENTS = "select * from students join users on(users.user_id = students.user_id)";

        Connection connection = PostgresSqlConnection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(GET_ALL_STUDENTS);

        while (rs.next()) {
            allStudents.add(new Student(rs.getString("surname"),rs.getString("name"),rs.getDate("dateofbirth").toLocalDate()));
        }

        rs.close();
        return allStudents;
    }
}
