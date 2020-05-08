package jdbc;

import exceptions.NameException;
import exceptions.UniqueException;
import model.User;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
//TODO install TOMCAT
//TODO find out how to create web project in IDE community
//TODO for group return List of Students that Unclassified (sorted by Surname)
//TODO initial script

public class UserRepository {


    public static User addUser(User user) throws SQLException {
        if(isUserPresent(user)){
            throw new UniqueException("This user is already in table!");
        }
        else {
            String ADD_NEW_USER = "INSERT INTO users (surname, name, dateofbirth, created_on)"
                    + "values(?,?,?,?)";

            try (Connection connection = PostgresSqlConnection.getConnection()) {
                PreparedStatement preparedStatement = connection.prepareStatement(ADD_NEW_USER,Statement.RETURN_GENERATED_KEYS);

                preparedStatement.setString(1, user.getSurname());
                preparedStatement.setString(2, user.getName());
                preparedStatement.setObject(3, user.getDOB());
                preparedStatement.setObject(4, LocalDateTime.now());
                preparedStatement.executeUpdate();

                ResultSet rs = preparedStatement.getGeneratedKeys();
                while(rs.next()){
                    user.setID(rs.getInt("user_id"));
                }
                System.out.println("User is successfully added! ");


            }
        }
        return user;
    }


    public static void deleteUser(int id) throws SQLException {
        String DELETE_USER = "DELETE FROM users WHERE user_id = ?";

        Connection connection = PostgresSqlConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER);
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        connection.close();
        System.out.println("User is successfully deleted! ");

    }


    public static List<User> getAllUsers() throws SQLException, NameException {
        List<User> allUsers = new ArrayList<>();
        String sql = "SELECT *  FROM users";

        Connection connection = PostgresSqlConnection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);

        while (rs.next()) {
            User user = new User();
            user.setSurname(rs.getString("surname"));
            user.setName(rs.getString("name"));
            user.setDOB(rs.getDate("dateOfBirth").toLocalDate());

            allUsers.add(user);
        }

        rs.close();
        return allUsers;
    }

    public static boolean editUser(User user) throws SQLException {

        String EDIT_USER = "UPDATE user SET surname=?, name=?, dateOfBirth=?,updated_at=?  " +
                " WHERE user_id = ? ;";
        try (Connection connection = PostgresSqlConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(EDIT_USER);
            preparedStatement.setString(1, user.getSurname());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setObject(3, user.getDOB());
            preparedStatement.setObject(4, LocalDateTime.now());
            preparedStatement.setInt(5, user.getID());

            return (preparedStatement.executeUpdate() != 0);
        }
    }

    public static int getUserIDByFullInformation(User user) throws SQLException, NameException {
        String GET_USER_BY_FULL_INFORMATION = "SELECT * FROM users WHERE surname = '" + user.getSurname() + "'" + "AND name = '" + user.getName() + "'" + "AND dateofbirth = '" + user.getDOB() + "'";
        try (Connection connection = PostgresSqlConnection.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(GET_USER_BY_FULL_INFORMATION);

            rs.next();
            return rs.getInt("user_id");
        }
    }

    public static boolean isUserPresent(User user) throws SQLException {

        String sql = "SELECT * FROM users WHERE surname = '" + user.getSurname() + "'" + "AND name = '" + user.getName() + "'" + "AND dateofbirth = '" + user.getDOB() + "'";
        Connection connection = PostgresSqlConnection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);

        while (rs.next()) {
            return true;
        }

        return false;
    }

    public static void createUserTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users" +
                "( user_id serial PRIMARY KEY, " +
                " surname VARCHAR(20) NOT NULL, " +
                "name VARCHAR(15) NOT NULL," +
                "dateOfBirth TIMESTAMP NOT NULL," +
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

    public static void deleteUserTable() {
        String sql = "DROP TABLE users";
        try (Connection connection = PostgresSqlConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.execute();
            System.out.println("Table is successfully deleted! ");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static List<User> getUsersByCurrentMonthViaUniqueInCollection() throws SQLException, NameException {
        List<User> allUsers = new ArrayList<>();
        String GET_ALL_USERS = "SELECT *  FROM users";
        try (Connection connection = PostgresSqlConnection.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(GET_ALL_USERS);

            while (rs.next()) {
                User user = new User();
                user.setSurname(rs.getString("surname"));
                user.setName(rs.getString("name"));
                user.setDOB(rs.getDate("dateOfBirth").toLocalDate());
                if (user.getDOB().getMonth() == LocalDate.now().getMonth()) {
                    allUsers.add(user);
                }
            }
        }
        return allUsers;
    }

    public static List<User> getUsersByCurrentMonthViaStream() throws SQLException, NameException {
        List<User> allUsers = new ArrayList<>();
        String GET_ALL_USERS = "SELECT *  FROM users";
        try (Connection connection = PostgresSqlConnection.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(GET_ALL_USERS);

            while (rs.next()) {
                User user = new User();
                user.setSurname(rs.getString("surname"));
                user.setName(rs.getString("name"));
                user.setDOB(rs.getDate("dateOfBirth").toLocalDate());
                allUsers.add(user);
            }


        }
        return allUsers.stream().filter(user -> user.getDOB()
               .getMonth().equals(LocalDate.now().getMonth()))
               .collect(Collectors.toList());
    }

    public static List<User> getUsersByCurrentMonthViaSqlStatement() throws SQLException, NameException {
        List<User> allUsers = new ArrayList<>();
        String GET_USERS_THAT_BORN_IN_CURRENT_MONTH = "SELECT * FROM users WHERE EXTRACT(MONTH FROM dateofbirth)=EXTRACT (MONTH FROM NOW());";
        try (Connection connection = PostgresSqlConnection.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(GET_USERS_THAT_BORN_IN_CURRENT_MONTH);

            while (rs.next()) {
                User user = new User();
                user.setSurname(rs.getString("surname"));
                user.setName(rs.getString("name"));
                user.setDOB(rs.getDate("dateOfBirth").toLocalDate());
                allUsers.add(user);
            }
        }
        return allUsers;
    }

}
