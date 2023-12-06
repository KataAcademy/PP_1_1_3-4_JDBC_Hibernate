package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        Util.getConnection();
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Krot", "Smit", (byte) 20);
        userService.saveUser("Zebra", "Zebrovski", (byte) 25);
        userService.saveUser("Pikachu", "ChuChu", (byte) 31);
        userService.saveUser("Man", "Last", (byte) 38);

        userService.removeUserById(1);
        userService.getAllUsers().forEach(System.out::println);
        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}
