package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Andrey", "Bradobrey", (byte)22);
        userService.saveUser("Roma", "Bystry", (byte)22);
        userService.saveUser("Andy", "Bystha", (byte)26);
        userService.saveUser("Kiska", "Lalala", (byte)23);
        userService.saveUser("Oleg", "Mongol", (byte)24);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
