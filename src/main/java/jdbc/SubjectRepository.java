package jdbc;

import exceptions.UniqueException;
import model.Subject;
import model.SubjectType;
import org.postgresql.util.PSQLException;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SubjectRepository {


    public static void addSubject(Subject subject) throws SQLException {

        String sql = "INSERT INTO subject (name, term, type, created_on)"
                + "values(?,?,?,?)";

        try (Connection connection = PostgresSqlConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, subject.getName());
            preparedStatement.setInt(2, subject.getTerm());
            preparedStatement.setString(3, subject.getType().toString());
            preparedStatement.setObject(4, LocalDate.now());

            preparedStatement.executeUpdate();
            System.out.println("Subject is successfully added! ");
        } catch (PSQLException e) {
            if (e.getMessage().contains("name_term_unique")) {
                throw new UniqueException("This subject is already in the table!!!");
            }
            throw e;

        }
    }

    public static void deleteSubjectFromTable(int id) throws SQLException {
        String DELETE_SUBJECT = "DELETE FROM subject WHERE id = ?";

        Connection connection = PostgresSqlConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SUBJECT);
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        connection.close();
        System.out.println("Subject is successfully deleted! ");

    }

    public static List<Subject> getAllSubjects() throws SQLException {
        List<Subject> allSubjects = new ArrayList<>();


        String sql = "SELECT *  FROM subject";
        Connection connection = PostgresSqlConnection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);

        while (rs.next()) {
            Subject subject = new Subject();
            subject.setName(rs.getString("name"));
            subject.setTerm(rs.getInt("term"));
            subject.setType(SubjectType.valueOf(rs.getString("type")));
            allSubjects.add(subject);
        }

        rs.close();
        return allSubjects;
    }

    public static boolean editSubject(Subject subject) throws SQLException {
        String SQL = "UPDATE actor "
                + "SET last_name = ? "
                + "WHERE actor_id = ?";
        String EDIT_SUBJECT = "UPDATE subject SET name=?, term=?, type=?,updated_at=?  " +
                "                  WHERE subject_id = ? ;";
        try (Connection connection = PostgresSqlConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(EDIT_SUBJECT);
            preparedStatement.setString(1, subject.getName());
            preparedStatement.setInt(2, subject.getTerm());
            preparedStatement.setString(3, subject.getType().toString());
            preparedStatement.setObject(4, LocalDateTime.now());
            preparedStatement.setInt(5, subject.getID());

            return (preparedStatement.executeUpdate() != 0);
        }
    }

    public static Optional<Subject> getSubjectByID(int id) throws SQLException {
        String GET_SUBJECT_BY_ID = "SELECT * FROM subject Where subject_id = " + id;
        try (Connection connection = PostgresSqlConnection.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(GET_SUBJECT_BY_ID);

            rs.next();
            Subject subject = new Subject();
            subject.setName(rs.getString("name"));
            subject.setTerm(rs.getInt("term"));
            subject.setType(SubjectType.valueOf(rs.getString("type")));
            subject.setID(rs.getInt("subject_id"));
            return Optional.of(subject);


        }
    }

    public static void createSubjectTable() {
        String sql = "CREATE TABLE IF NOT EXISTS subject" +
                "( subject_id serial PRIMARY KEY, " +
                " name VARCHAR (50) NOT NULL, " +
                "term INTEGER NOT NULL," +
                " type VARCHAR (10) NOT NULL," +
                " created_on TIMESTAMP NOT NULL, updated_at TIMESTAMP,  constraint name_term_unique Unique(name,term))";

        try (Connection connection = PostgresSqlConnection.getConnection();
             Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("New table successfully created! ");

        } catch (SQLException e) {
            System.out.println("Connection failure!");
            e.getStackTrace();
        }
    }


    public static void deleteSubjectTable() {
        String sql = "DROP TABLE subject";
        try (Connection connection = PostgresSqlConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.execute();
            System.out.println("Table is successfully deleted! ");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static boolean isSubjectPresent(Subject subject) throws SQLException {

        String sql = "SELECT * FROM subject WHERE name = '" + subject.getName() + "'" + "AND type = '" + subject.getType() + "'" + "AND term = '" + subject.getTerm() + "'";
        Connection connection = PostgresSqlConnection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);

        while (rs.next()) {
            return true;
        }
        return false;
    }


    public static int getSubjectID(Subject subject) throws SQLException {

        String sql = "SELECT * FROM subject WHERE name = '" + subject.getName() + "'" + "AND type = '" + subject.getType() + "'" + "AND term = '" + subject.getTerm() + "'";
        Connection connection = PostgresSqlConnection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        int subject_id =0;
        while (rs.next()) {
         subject_id= rs.getInt("subject_id");
        }

     return subject_id;
    }

}
