package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    static UserServiceImpl userService = new UserServiceImpl();
    public static void main(String[] args) {

        userService.createUsersTable();
        userService.saveUser("Max", "Pasevich", (byte) 21);
        userService.saveUser("Svetlana", "Grijeliyk", (byte) 18);
        userService.saveUser("Ivan", "Ivanov", (byte) 99);
        userService.saveUser("Oleg", "OLegov", (byte) 99);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();


    }

}
