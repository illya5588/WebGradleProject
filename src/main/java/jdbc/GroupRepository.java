package jdbc;

import exceptions.NameException;
import exceptions.UniqueException;
import model.Group;
import model.Student;

import java.sql.*;
import java.util.*;


public class GroupRepository {
    private static String ADD_NEW_GROUP = "INSERT INTO groups (group_name, created_on)"
            + "values(?,?)";
    private static String GET_ALL_GROUPS = "SELECT * FROM groups";
    private static String GET_STUDENT_BY_USER_ID = " select users.surname, users.name, users.dateofbirth, students.student_id from students join users on(users.user_id = students.user_id) ";

    public static void addGroup(Group group) throws SQLException, NameException {
        if (isGroupPresent(group)) {
            throw new UniqueException("This group is already exists in the table!");
        }
        executePreparedStatementToAddGroup(group);
    }

    public static void addStudentToGroup(Student student, Group group) throws SQLException {
        String ADD_STUDENT_TO_GROUP = "Update students set  group_id =" + getGroupId(group) + " where student_id=" + StudentRepository.getStudentIdByFullInformation(student) + ";";
        try (Connection connection = PostgresSqlConnection.getConnection();
             Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(ADD_STUDENT_TO_GROUP);
        } catch (SQLException e) {
            e.getMessage();
            e.getStackTrace();
        }
    }
    public static List<Group> getAllGroups() {
        List<Group> groups = new ArrayList<>();
        Connection connection = null;

        try {
            connection = PostgresSqlConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(GET_ALL_GROUPS);

            while (rs.next()) {
                Group group = new Group(rs.getString("group_name"), rs.getDate("created_on").toLocalDate());
                group.setId(rs.getInt("group_id"));
                group.setGroupList(GroupRepository.getStudentsByGroup(group));
                groups.add(group);
            }
            return groups;
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }


    }

    private static void executePreparedStatementToAddGroup(Group group) throws SQLException, NameException {

        try (Connection connection = PostgresSqlConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_NEW_GROUP, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, group.getName());
            preparedStatement.setObject(2, group.getDateOfCreation());

            preparedStatement.executeUpdate();

            System.out.println("Group is successfully added!");
        }
    }

    public static void createGroupTable() {
        String sql = "CREATE TABLE IF NOT EXISTS groups" +
                "( group_id serial PRIMARY KEY, " +
                "group_name VARCHAR(50), created_on TIMESTAMP NOT NULL, updated_at TIMESTAMP)";

        try (Connection connection = PostgresSqlConnection.getConnection();
             Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(sql);

            System.out.println("New table successfully created! ");

        } catch (SQLException e) {
            System.out.println("Something wrong!!! Table is not created!");
            e.getStackTrace();
        }
    }

    public static void deleteGroupTable() {
        String sql = "DROP TABLE groups";
        try (Connection connection = PostgresSqlConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.execute();
            System.out.println("Table is successfully deleted! ");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static Set<Student> getAllUnclassifiedStudentsByGroup(Group group) throws SQLException, NameException {
        Set<Student> allStudents = new TreeSet<>();
        String sql = "select users.surname, users.name, users.dateofbirth, students.student_id from students join marks on(students.student_id = marks.student_id) join users on(users.user_id=students.user_id) where students.group_name = '" + group.getName() + "' and marks.mark<=50;";

        Connection connection = PostgresSqlConnection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);

        while (rs.next()) {
            Student student = new Student(rs.getString("surname"), rs.getString("name"), rs.getDate("dateofbirth").toLocalDate());
            allStudents.add(student);
        }

        rs.close();
        return allStudents;
    }

    public static boolean isGroupPresent(Group group) throws SQLException {

        String sql = "select group_id from groups where group_name= '" + group.getName() + "' AND created_on = '" + group.getDateOfCreation() + "'";
        Connection connection = PostgresSqlConnection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);

        while (rs.next()) {
            return true;
        }

        return false;
    }

    public static int getGroupId(Group group) throws SQLException, NameException {
        String GET_GROUP_ID = "select group_id from groups where group_name = '" + group.getName() + "' AND created_on = '" + group.getDateOfCreation() + "'";
        try (Connection connection = PostgresSqlConnection.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(GET_GROUP_ID);

            rs.next();
            return rs.getInt("group_id");
        }
    }

    public static Group getGroupById(int id) throws SQLException, NameException {
        String GET_GROUP_BY_ID = "select * from groups where group_id = " + id + ";";
        try (Connection connection = PostgresSqlConnection.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(GET_GROUP_BY_ID);

            rs.next();
            Group group = new Group();
            group.setName(rs.getString("group_name"));
            group.setDateOfCreation(rs.getDate("created_on").toLocalDate());
            group.setId(rs.getInt("group_id"));
            return group;
        }
    }

    public static Set<Student> getStudentsByGroup(Group group) throws NameException, SQLException {
        String GET_GROUP_LIST = GET_STUDENT_BY_USER_ID+" join groups on(groups.group_id = students.group_id) where students.group_id = "+group.getId();
       Set<Student> groupStudents = new HashSet<>();
        Connection connection = PostgresSqlConnection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(GET_GROUP_LIST);

        while (rs.next()) {
         Student student=   new Student(rs.getString("surname"), rs.getString("name"), rs.getDate("dateofbirth").toLocalDate());
         student.setStudent_ID(rs.getInt("student_id"));
            groupStudents.add(student);
        }
        return groupStudents;

    }


}
