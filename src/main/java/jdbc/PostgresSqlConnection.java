package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class PostgresSqlConnection {
    private static final String LOGIN = "postgres";
    private static final String PASSWORD = "290798is";
    private static final String URL = "jdbc:postgresql://localhost:5432/test2";



    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("PostgreSQL DataSource unable to load PostgreSQL JDBC Driver");
        }
        Connection connection = DriverManager.getConnection(URL,LOGIN,PASSWORD);
        return connection;
    }

    public static void main(String[] args)  {
        try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD)) {
            System.out.println("Connection to Data Base is SUCCESSFUL! ");

            //GroupRepository.createGroupTable();
           // StudentRepository.createStudentTable();




        } catch (SQLException e) {
            System.out.println("Oooops, something wrong!");
            e.printStackTrace();
        }
    }








}



