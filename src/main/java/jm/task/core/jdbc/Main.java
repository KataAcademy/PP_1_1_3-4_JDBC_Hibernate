package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Иван","Иванов",(byte) 46);
        userService.saveUser("Петр","Петров",(byte) 19);
        userService.saveUser("Сидор","Сидоров",(byte) 24);
        userService.saveUser("Алексей", "Алексеев",(byte) 32);

        System.out.println(userService.getAllUsers().toString());

        userService.cleanUsersTable();
        //System.out.println(userService.getAllUsers().toString());

        userService.dropUsersTable();
       // System.out.println(userService.getAllUsers().toString());
    }
}