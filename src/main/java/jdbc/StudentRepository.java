package jdbc;

import exceptions.MarkException;
import exceptions.NameException;
import exceptions.UniqueException;
import model.*;
import org.postgresql.util.PSQLException;
import service.StudentService;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;


public class StudentRepository {
    private static int LIMIT = 5;

    private static String ADD_NEW_STUDENT = "INSERT INTO students (student_uuid,user_id,created_on)"
            + "values(?,?,?)";
    private static String ADD_MARKS = "INSERT INTO marks (student_id,subject_id,mark,created_on)"
            + "values(?,?,?,?)";

    //TODO sort by group
    //TODO buttons (by surname, by group(by surname))
    //TODO service and DTO
    //TODO pagination
    public static Map<Integer, List<Student>> getStudentsByPage() throws SQLException {
        int numberOfPages = numOfPages();
        int start = 0;
        Map<Integer, List<Student>> studentPages = new HashMap<>();
        for (int i = 1; i <= numberOfPages; i++) {
            studentPages.put(i, getPage(start));
            start = start + 5;
        }
        return studentPages;
    }

    //TODO start, limit, rename
    public static List<Student> getPage(int page) throws SQLException {
        int start = (page - 1) * 5;
        List<Student> allStudents = new ArrayList<>();
        String GET_ALL_STUDENTS = "select * from students join users on(users.user_id = students.user_id) limit " + LIMIT + " offset  " + start;

        Connection connection = PostgresSqlConnection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(GET_ALL_STUDENTS);

        while (rs.next()) {
            Student student = new Student(rs.getString("surname"), rs.getString("name"), rs.getDate("dateofbirth").toLocalDate());
            student.setStudent_ID(rs.getInt("student_id"));
            if (rs.getInt("group_id") != 0) {
                student.setGroup(GroupRepository.getGroupById(Integer.valueOf(rs.getInt("group_id"))));
            }

            allStudents.add(student);

        }

        rs.close();
        return allStudents;
    }

    public static List<Student> getPageBySurname(int page, String order, String success) throws SQLException {
        int start = (page - 1) * LIMIT;
        List<Student> allStudents = new ArrayList<>();
        String GET_ALL_STUDENTS = "select * from students join users on(users.user_id = students.user_id) order by surname " + order + "  limit " + LIMIT + " offset  " + start;
        String GET_ALL_UNCLASSIFIED_STUDENTS = "select distinct surname, name, dateOfBirth, students.student_id, group_id from students join users on(users.user_id = students.user_id) join marks on(students.student_id=marks.student_id) where mark<50 order by surname " + order + "  limit " + LIMIT + " offset  " + start;
        Connection connection = PostgresSqlConnection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = null;
        if("unclassified".equals(success)){
             rs = statement.executeQuery(GET_ALL_UNCLASSIFIED_STUDENTS);
        } else {
            rs = statement.executeQuery(GET_ALL_STUDENTS);
        }
        while (rs.next()) {
            Student student = new Student(rs.getString("surname"), rs.getString("name"), rs.getDate("dateofbirth").toLocalDate());
            student.setStudent_ID(rs.getInt("student_id"));
            if (rs.getInt("group_id") != 0) {
                student.setGroup(GroupRepository.getGroupById(Integer.valueOf(rs.getInt("group_id"))));
            }

            allStudents.add(student);

        }

        rs.close();
        return allStudents;
    }

    public static int getPagesBySurname(String success) throws SQLException {
        int result=0;
        String GET_ALL_STUDENTS = "select count (*) from students join users on(users.user_id = students.user_id) " ;
        String GET_ALL_UNCLASSIFIED_STUDENTS = "select count( distinct surname) from students join users on(users.user_id = students.user_id) join marks on(students.student_id=marks.student_id) where mark<50";
        Connection connection = PostgresSqlConnection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = null;
        if("unclassified".equals(success)){
            rs = statement.executeQuery(GET_ALL_UNCLASSIFIED_STUDENTS);
        } else {
            rs = statement.executeQuery(GET_ALL_STUDENTS);
        }
        while (rs.next()) {
            result=rs.getInt(1);



        }

        rs.close();
        return result;
    }

    public static List<Student> getPageByGroup(int page, String order) throws SQLException {
        int start = (page - 1) * LIMIT;
        List<Student> allStudents = new ArrayList<>();
        String GET_ALL_STUDENTS = "select * from students join users on(users.user_id = students.user_id) order by group_id " + order + " , surname ASC  limit " + LIMIT + " offset  " + start;

        Connection connection = PostgresSqlConnection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(GET_ALL_STUDENTS);

        while (rs.next()) {
            Student student = new Student(rs.getString("surname"), rs.getString("name"), rs.getDate("dateofbirth").toLocalDate());
            student.setStudent_ID(rs.getInt("student_id"));
            if (rs.getInt("group_id") != 0) {
                student.setGroup(GroupRepository.getGroupById(Integer.valueOf(rs.getInt("group_id"))));
            }

            allStudents.add(student);

        }

        rs.close();
        return allStudents;
    }

    public static int numOfPages() throws SQLException {
        int numOfPages = getNumberOfStudents() / 5;
        if (getNumberOfStudents() % 5 != 0) {
            numOfPages++;
        }
        return numOfPages;
    }

    public static int getNumberOfStudents() throws SQLException {
        int numberOfStudents = getAllStudents().size();
        return numberOfStudents;
    }

    //TODO change to GroupRep
    private static Set<Student> getStudentsByCriteria(String sql) throws SQLException {
        Set<Student> students = new LinkedHashSet<>();
        Connection connection = PostgresSqlConnection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);

        while (rs.next()) {
            Student student = new Student(rs.getString("surname"), rs.getString("name"), rs.getDate("dateofbirth").toLocalDate());
            if (rs.getInt("group_id") != 0) {
                student.setGroup(GroupRepository.getGroupById(rs.getInt("group_id")));
            }
            student.setStudent_ID(rs.getInt("student_id"));
            students.add(student);
        }

        rs.close();
        return students;
    }

    public static Map<Subject, Mark> getStudentMarks(int studentId) throws SQLException, MarkException {
        Map<Subject, Mark> marks = new HashMap<>();
        String GET_STUDENT_MARKS = "select*from marks join students on(students.student_id = marks.student_id) where students.student_id=" + studentId;
        Connection connection = PostgresSqlConnection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(GET_STUDENT_MARKS);

        while (rs.next()) {

            marks.put(SubjectRepository.getSubjectByID(rs.getInt("subject_id")).get(), new Mark(rs.getInt("mark")));
        }

        rs.close();
        return marks;
    }

    public static Set<Student> getStudentsBySurname() throws SQLException {

        String GET_STUDENTS_BY_SURNAME = "select * from students join users on(students.user_id = users.user_id) order by surname ASC;";
        return getStudentsByCriteria(GET_STUDENTS_BY_SURNAME);
    }

    public static Set<Student> getStudentsByGroupAndSurname() throws SQLException {

        String GET_STUDENTS_BY_SURNAME_AND_GROUP = "select * from students join users on(students.user_id = users.user_id) order by group_id DESC, surname ASC;";
        return getStudentsByCriteria(GET_STUDENTS_BY_SURNAME_AND_GROUP);
    }


    public static Set<Student> getUnclassified() throws SQLException {

        String UNCLASSIFIED_STUDENTS = "select users.surname, users.name, users.dateofbirth, students.student_id, students.group_id from students join marks on(students.student_id = marks.student_id) join users on(users.user_id=students.user_id) where marks.mark<=50;";
        return getStudentsByCriteria(UNCLASSIFIED_STUDENTS);
    }

    public static List<Student> searchStudents(String parameter) throws SQLException {
        List<Student> allStudents = new ArrayList<>();
        String SEARCH_STUDENTS = "Select * From students join users on (users.user_id = students.user_id) WHERE surname ilike '%" + parameter + "%' or name ilike'%" + parameter + "%';";
        Connection connection = PostgresSqlConnection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(SEARCH_STUDENTS);
        while (rs.next()) {
            Student student = new Student(rs.getString("surname"), rs.getString("name"), rs.getDate("dateofbirth").toLocalDate());
            student.setStudent_ID(rs.getInt("student_id"));
            allStudents.add(student);

        }

        rs.close();
        statement.close();
        connection.close();
        return allStudents;
    }

    public static Student addStudent(Student student) throws SQLException, NameException {
        if (UserRepository.isUserPresent(student)) {
            if (isStudentPresent(student)) {
                throw new UniqueException("This student is already in the table!!!");
            } else {
                if (!GroupRepository.isGroupPresent(student.getGroup())) {
                    GroupRepository.addGroup(student.getGroup());
                }
                executePreparedStatementToAddStudent(student);
                return student;
            }
        }
        User user = UserRepository.addUser(student);
        executePreparedStatementToAddStudent(student);
        return student;
    }

    private static void executePreparedStatementToAddStudent(Student student) throws SQLException, NameException {

        try (Connection connection = PostgresSqlConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_NEW_STUDENT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, student.getStudent_uuid());
            preparedStatement.setInt(2, UserRepository.getUserIDByFullInformation(student));
            preparedStatement.setObject(3, LocalDateTime.now());
            preparedStatement.executeUpdate();

            System.out.println("Student is successfully added!");
        }
    }


    public static void addMark(Student student) throws SQLException, NameException {
        Map<Subject, Mark> buf = student.getMarks();
        for (Subject subject : buf.keySet()) {
            if (SubjectRepository.isSubjectPresent(subject)) {
                if (isStudentMarkPresent(student, subject)) {
                    System.err.println("Student's Mark " + buf.get(subject).getDigitMark() + " in subject " + subject.getName() + " is already in the table");
                } else {


                    try (Connection connection = PostgresSqlConnection.getConnection()) {
                        PreparedStatement preparedStatement = connection.prepareStatement(ADD_MARKS, Statement.RETURN_GENERATED_KEYS);

                        preparedStatement.setInt(1, StudentRepository.getStudentIdByFullInformation(student));
                        preparedStatement.setInt(2, SubjectRepository.getSubjectID(subject));
                        preparedStatement.setInt(3, buf.get(subject).getDigitMark());
                        preparedStatement.setObject(4, LocalDateTime.now());

                        preparedStatement.executeUpdate();
                    } catch (PSQLException e) {
                        throw new UniqueException("This student mark is already exists!!!");
                    }
                }
            } else {
                SubjectRepository.addSubject(subject);


                try (Connection connection = PostgresSqlConnection.getConnection()) {
                    PreparedStatement preparedStatement = connection.prepareStatement(ADD_MARKS, Statement.RETURN_GENERATED_KEYS);

                    preparedStatement.setInt(1, StudentRepository.getStudentIdByFullInformation(student));
                    preparedStatement.setInt(2, SubjectRepository.getSubjectID(subject));
                    preparedStatement.setInt(3, buf.get(subject).getDigitMark());
                    preparedStatement.setObject(4, LocalDateTime.now());

                    preparedStatement.executeUpdate();
                }
            }
        }

    }

    public static void addMarkForStudent(Student student, Subject subject, int mark) throws SQLException, NameException {
        if (getStudentIdByFullInformation(student) == 0 || SubjectRepository.getSubjectID(subject) == 0) {
            throw new IllegalArgumentException("This student or subject does not exist");
        }
        String ADD_MARKS = "INSERT INTO marks (student_id,subject_id,mark,created_on)"
                + "values(?,?,?,?)";

        try (Connection connection = PostgresSqlConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_MARKS);

            preparedStatement.setInt(1, StudentRepository.getStudentIdByFullInformation(student));
            preparedStatement.setInt(2, SubjectRepository.getSubjectID(subject));
            preparedStatement.setInt(3, mark);
            preparedStatement.setObject(4, LocalDateTime.now());

            preparedStatement.executeUpdate();
        }
    }

    public static void addMarksForStudent(Student student, Map<Subject, Integer> marks) throws SQLException, NameException {
        for (Subject subject : marks.keySet()) {
            addMarkForStudent(student, subject, marks.get(subject));
        }
    }

    public static void createStudentTable() {
        String sql = "CREATE TABLE IF NOT EXISTS students" +
                "( student_id serial PRIMARY KEY, " +
                " student_uuid UUID NOT NULL, " +
                "user_id INTEGER REFERENCES users(user_id), " +
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
                "subject_id INTEGER REFERENCES subject(subject_id), " +
                "mark INTEGER NOT NULL," +
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

        String sql = "select student_id from students join users on (users.user_id = students.user_id) where users.surname = '" + student.getSurname() + "' AND users.name = '" + student.getName() + "' AND users.dateofbirth = '" + student.getDOB() + "'";
        Connection connection = PostgresSqlConnection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);

        while (rs.next()) {
            return true;
        }

        return false;
    }
    public static boolean isStudentPresentByUserId(int userId) throws SQLException {

        String sql = "select student_id from students  where user_id="+userId+";";
        Connection connection = PostgresSqlConnection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);

        while (rs.next()) {
            return true;
        }

        return false;
    }
    public static boolean addStudentByUserId(int userId) throws SQLException {
        if(!(isStudentPresentByUserId(userId))){
            try (Connection connection = PostgresSqlConnection.getConnection()) {
                PreparedStatement preparedStatement = connection.prepareStatement(ADD_NEW_STUDENT, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setObject(1, Student.genID());
                preparedStatement.setInt(2, userId);
                preparedStatement.setObject(3, LocalDateTime.now());
                preparedStatement.executeUpdate();
                System.out.println("Student is successfully added!");
                return true;
            }

        }
        return false;
    }

    public static int getStudentIdByFullInformation(Student student) throws SQLException, NameException {
        String sql = "select student_id from students join users on (users.user_id = students.user_id) where users.surname = '" + student.getSurname() + "' AND users.name = '" + student.getName() + "' AND users.dateofbirth = '" + student.getDOB() + "'";
        try (Connection connection = PostgresSqlConnection.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            rs.next();
            return rs.getInt("student_id");
        }
    }

    public static boolean isStudentMarkPresent(Student student, Subject subject) throws SQLException, NameException {

        String sql = " select*from marks where student_id = '" + StudentRepository.getStudentIdByFullInformation(student) + "' and subject_id = ' " + SubjectRepository.getSubjectID(subject) + "' and mark = ' " + student.getMarks().get(subject).getDigitMark() + " '";
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
            Student student = new Student(rs.getString("surname"), rs.getString("name"), rs.getDate("dateofbirth").toLocalDate());
            student.setStudent_ID(rs.getInt("student_id"));
            if (rs.getInt("group_id") != 0) {
                student.setGroup(GroupRepository.getGroupById(Integer.valueOf(rs.getInt("group_id"))));
            }

            allStudents.add(student);

        }

        rs.close();
        return allStudents;
    }

    public static void editStudentGroup(Student student) {
        String EDIT_STUDENT_GROUP = "Update students set  group_id =" + student.getGroup().getId() + " where student_id=" + student.getStudent_ID() + ";";
        try (Connection connection = PostgresSqlConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(EDIT_STUDENT_GROUP)) {
            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void editStudent(Student student) throws NameException, SQLException {
        String EDIT_STUDENT = "Update users set surname = '" + student.getSurname() + "', name  = '" + student.getName() + "', dateofbirth ='" + student.getDOB() + "' where user_id = (select user_id from students where student_id= '" + student.getStudent_ID() + "' );";
        try (Connection connection = PostgresSqlConnection.getConnection();
             Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(EDIT_STUDENT);
        } catch (SQLException e) {
            e.getMessage();
            e.getStackTrace();
        }
    }

    public static Optional<Student> getStudentById(int id) throws SQLException {
        String GET_STUDENT_BY_ID = "select * from students left join groups on groups.group_id  = students.group_id join users on users.user_id = students.user_id where student_id=" + id + ";";

        Connection connection = PostgresSqlConnection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(GET_STUDENT_BY_ID);

        while (rs.next()) {

            Student student = new Student(rs.getString("surname"), rs.getString("name"), rs.getDate("dateofbirth").toLocalDate());
            Group group = new Group();
            if (rs.getObject("group_id") == null) {
                student.setGroup(null);
            } else {
                group.setId(rs.getInt("group_id"));
                group.setName(rs.getString("group_name"));
                student.setGroup(group);
                student.setStudent_ID(id);
            }
            return Optional.of(student);
        }

        rs.close();
        return Optional.empty();
    }

    public static void deleteStudentById(int id) {
        String DELETE_STUDENT = "DELETE FROM students where student_id=" + id;
        try (Connection connection = PostgresSqlConnection.getConnection();
             Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(DELETE_STUDENT);
        } catch (SQLException e) {
            e.getMessage();
            e.getStackTrace();
        }
    }
}
