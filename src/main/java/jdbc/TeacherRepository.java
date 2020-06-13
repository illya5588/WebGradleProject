package jdbc;

import exceptions.NameException;
import exceptions.UniqueException;
import model.Student;
import model.Teacher;

import java.sql.*;
import java.time.LocalDateTime;

//TODO SQL Injection
//TODO remake addTeacher

public class TeacherRepository {
  private static String ADD_NEW_TEACHER = "INSERT INTO teachers (department,user_id,created_on)"
            + "values(?,?,?)";


    public static void addTeacher(Teacher teacher) throws SQLException, NameException {

        if (UserRepository.isUserPresent(teacher)) {
            if (isTeacherPresent(teacher)) {
                throw new UniqueException("This teacher is already in the table!!!");
            } else {
               getPreparedStatementToAddTeacher(teacher);
            }
        } else {
            UserRepository.addUser(teacher);
            getPreparedStatementToAddTeacher(teacher);


        }

    }
    public static boolean addTeacherByUserId(int userId) throws SQLException {
        String ADD_NEW_TEACHER = "INSERT INTO teachers (department,user_id,created_on)"
                + "values(?,?,?)";
        if(!(isTeacherPresentByUserId(userId))){
            try (Connection connection = PostgresSqlConnection.getConnection()) {
                PreparedStatement preparedStatement = connection.prepareStatement(ADD_NEW_TEACHER);
                preparedStatement.setString(1, "java");
                preparedStatement.setInt(2, userId);
                preparedStatement.setObject(3, LocalDateTime.now());
                preparedStatement.executeUpdate();
                System.out.println("Teacher is successfully added!");
                return true;
            }

        }
        return false;
    }
    public static boolean isTeacherPresentByUserId(int userId) throws SQLException {

        String sql = "select teacher_id from teachers  where user_id="+userId+";";
        Connection connection = PostgresSqlConnection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);

        while (rs.next()) {
            return true;
        }

        return false;
    }

     private static void getPreparedStatementToAddTeacher(Teacher teacher) throws SQLException, NameException {
         try (Connection connection = PostgresSqlConnection.getConnection()) {
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_NEW_TEACHER);

             preparedStatement.setString(1, teacher.getDepartment());
             preparedStatement.setInt(2, UserRepository.getUserIDByFullInformation(teacher));
             preparedStatement.setObject(3, LocalDateTime.now());


             preparedStatement.executeUpdate();
             System.out.println("Teacher is successfully added!");
         }
     }

    public static boolean isTeacherPresent(Teacher teacher) throws SQLException {

        String sql = "SELECT department, surname, name FROM teachers JOIN users on users.user_id = teachers.user_id WHERE department = '" + teacher.getDepartment() + "'" + "AND surname = '" + teacher.getSurname() + "'" + "AND name = '" + teacher.getName() + "'" + "AND dateofbirth = '" + teacher.getDOB() + "'";
        Connection connection = PostgresSqlConnection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);

        while (rs.next()) {
            return true;
        }

        return false;
    }

    public static void createTeacherTable() {
        String sql = "CREATE TABLE IF NOT EXISTS teachers" +
                "( teacher_id serial PRIMARY KEY, " +
                " department VARCHAR (30) NOT NULL, " +
                "user_id INTEGER REFERENCES users(user_id) NOT NULL, " +
                " created_on TIMESTAMP NOT NULL, updated_at TIMESTAMP)";

        try (Connection connection = PostgresSqlConnection.getConnection();
             Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("New table successfully created! ");

        } catch (SQLException e) {
            System.out.println("Connection failure!");
            e.getStackTrace();
        }
    }

    public static void deleteTeacherTable() {
        String sql = "DROP TABLE teachers";
        try (Connection connection = PostgresSqlConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.execute();
            System.out.println("Table is successfully deleted! ");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
