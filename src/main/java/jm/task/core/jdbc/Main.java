package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;


public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Alex", "Gusev", (byte) 18);
        userService.saveUser("Iron", "Man", (byte) 32);
        userService.saveUser("Kata", "Academy", (byte) 6);
        userService.saveUser("German", "Sevostyanov", (byte) 32);


        System.out.println(userService.getAllUsers());
        userService.getAllUsers();
        userService.dropUsersTable();

    }
}
