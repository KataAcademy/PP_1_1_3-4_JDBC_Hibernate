package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Kirill", "Olenichev", (byte) 28);
        userService.saveUser("Sergey", "Olenichev", (byte) 63);
        userService.saveUser("Richard", "Olenichev", (byte) 9);
        userService.saveUser("Dmitry", "Palcev", (byte) 27);

        userService.removeUserById(2);

        List<User> userList = userService.getAllUsers();
        System.out.println();
        for (User user : userList) {
            System.out.println(user);
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
