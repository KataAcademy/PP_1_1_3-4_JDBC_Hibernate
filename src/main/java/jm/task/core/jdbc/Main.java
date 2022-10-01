package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        // реализуйте алгоритм здесь
        UserServiceImpl userService = new UserServiceImpl();

        System.out.println("-------------------------------------------------\n");

        userService.createUsersTable();

        System.out.println("\n-------------------------------------------------\n");

        userService.saveUser("Алекс", "Авличев", (byte) 29);
        userService.saveUser("Максим", "Сидоренко", (byte) 40);
        userService.saveUser("Герман", "Севастьянов", (byte) 26);
        userService.saveUser("Вячеслав", "Воронцов", (byte) 35);

        System.out.println("\n-------------------------------------------------\n");

        userService.getAllUsers();

        System.out.println("\n-------------------------------------------------\n");

        userService.cleanUsersTable();

        System.out.println("\n-------------------------------------------------\n");

        userService.dropUsersTable();

        System.out.println("-------------------------------------------------\n");
    }
}
