package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();
        userService.createUsersTable();

        userService.saveUser("Andrey", "Pechterev", (byte) 24);
        userService.saveUser("Ivan", "Pechterev", (byte) 24);
        userService.saveUser("Nikolay", "Pogosov", (byte) 21);
        userService.saveUser("Artyom", "Skuryatin", (byte) 20);

        userService.removeUserById(1);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}
