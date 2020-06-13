package service;

import jdbc.StudentRepository;
import jdbc.UserRepository;
import model.User;

import java.sql.SQLException;

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
}
