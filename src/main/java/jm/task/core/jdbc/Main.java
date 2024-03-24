package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;


public class Main {
    public static void main(String[] args) throws SQLException {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Blss", "C5ht", (byte) 32);
        userService.saveUser("Bbd", "Cgr", (byte) 31);
        userService.saveUser("Bke", "Cfew", (byte) 23);
        userService.saveUser("Lb4", "Cvvv", (byte) 13);
        userService.getAllUsers().forEach(System.out::println);
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
