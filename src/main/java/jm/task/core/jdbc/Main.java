package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();
        List<User> users = new ArrayList<>();
        String[] names = {"Alex", "Bob", "Alice", "Ron"};
        String[] lastNames = {"Collins", "Rockwell", "Peters", "McDowel"};
        Byte[] ages = {5, 12, 54, 32};
        for (int i = 0; i < 4; i++) {
            User user = new User();
            user.setName(names[i]);
            user.setLastName(lastNames[i]);
            user.setAge(ages[i]);
            users.add(user);
        }

        userService.createUsersTable();
        for (User u:users) {
            try {
                userService.saveUser(u.getName(), u.getLastName(), u.getAge());
                System.out.println("User с именем " + u.getName() + " добавлен в базу данных.");
            } catch (RuntimeException e) {
                e.getMessage();
            }
        }

        List<User> usersFromDataBase = userService.getAllUsers();
        for (User u : usersFromDataBase) {
            System.out.println(u.toString());
        }

        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
