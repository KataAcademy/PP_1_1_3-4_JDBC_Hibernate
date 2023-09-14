package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        User user0 = new User("Bob", "Collins", (byte) 23);
        User user1 = new User("Alice", "Peters", (byte) 21);
        User user2 = new User("Ron", "McCullan", (byte) 28);
        User user3 = new User("Steve", "Standford", (byte) 43);
        List<User> usersFromDataBase = new ArrayList<>();
        List<User> users = new ArrayList<>();
        users.add(user0);
        users.add(user1);
        users.add(user2);
        users.add(user3);

        for (User user : users) {
            userService.saveUser(user.getName(), user.getLastName(), user.getAge());
            System.out.println("User с именем - " + user.getName() + " добавлен в базу данных");
        }

        usersFromDataBase = userService.getAllUsers();
        for (User u : usersFromDataBase) {
            System.out.println(u.toString());
        }

        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}
