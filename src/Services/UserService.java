package Services;

import Domain.UserDAO;
import Entities.User;
import Utils.ConsolePrinter;

import java.sql.SQLException;
import java.util.*;

public class UserService {
    UserDAO userDAO = new UserDAO();

    public void createAccount(String name, String email) {
        User user = new User(name, email, null);
        userDAO.save(user);
    }

    public void modifyAccount(String name, String email, Integer id){
        Optional<User> userOptional =  userDAO.findById(id);

        if (userOptional.isPresent()) {
            userDAO.update(new User(name, email, id));
        } else {
            ConsolePrinter.printInfo("User with id " + id + " not found.");
        }
    }

    public void deleteAccount(Integer id) {
        Optional<User> userOptional =  userDAO.findById(id);

        if (userOptional.isPresent()) {
            userDAO.delete(id);
        } else {
            ConsolePrinter.printInfo("User with id " + id + " not found.");
        }
    }

    public void displayAllAccounts() {
        Optional<List<User>> users = userDAO.findAll();
        if (users.isPresent()) {
            users.get().forEach(System.out::println);
        } else {
            ConsolePrinter.printInfo("No users exist.");
        }
    }

    public Optional<User> findById(Integer id) {
        Optional<User> user = userDAO.findById(id);
        if (user.isPresent()) {
            return user;
        } else {
            ConsolePrinter.printInfo("No users exist.");
            return Optional.empty();
        }
    }

}
