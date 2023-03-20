package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        User user1 = new User("Jon", "Malachite", (byte) 46);
        User user2 = new User("Mia", "Stone", (byte) 26);
        User user3 = new User("Janny", "Lee", (byte) 16);
        User user4 = new User("Jack", "Hill", (byte) 66);
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser(user1.getName(), user1.getLastName(), user1.getAge());
        userService.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
        userService.saveUser(user3.getName(), user3.getLastName(), user3.getAge());
        userService.saveUser(user4.getName(), user4.getLastName(), user4.getAge());
        List<User> usersList = userService.getAllUsers();
        for (User user : usersList) {
            System.out.println(user.toString());
        }

        userService.cleanUsersTable();
        System.out.println(userService.getAllUsers().size());
        userService.dropUsersTable();


    }
}
