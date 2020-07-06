package service;

import jdbc.StudentRepository;
import jdbc.UserRepository;
import model.User;

import java.sql.SQLException;
import java.util.List;

//TODO department, role as Enum
public class UserService {
    public static void registerUser(User user) throws SQLException {
       switch (user.getRole()){
           case "student":
               user= UserRepository.addUser(user);
               UserRepository.markConfirmed(user.getID(),user.getRole());
               break;
           case "teacher":
               UserRepository.addUser(user);
               break;
       }
    }
    public static List<User> getUnconfirmedUsers() throws SQLException {
        return UserRepository.getUnconfirmedUsers();
    }
    public static List<User> confirmUser(int id, String role) throws SQLException {
        switch (role) {
            case "student":
                StudentService.confirmStudent(id);
                break;
            case "teacher":
                TeacherService.confirmTeacher(id);
                break;
        }
        return getUnconfirmedUsers();
    }

    public static User getUserByLoginAndPassword(String login, String password) throws SQLException {
        return UserRepository.getUserByLoginAndPassword(login,password);
    }
}
