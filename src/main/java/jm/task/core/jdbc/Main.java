package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    private final static UserServiceImpl userService = new UserServiceImpl();

    public static void main(String[] args) {
        userService.createUsersTable();

        userService.saveUser("Джони", "Мнемоник", (byte) 28);
        userService.saveUser("Иван", "Сусанин", (byte) 64);
        userService.saveUser("Барак", "Обама", (byte) 60);
        userService.saveUser("Димон", "из бумера", (byte) 34);

        userService.getAllUsers().forEach(System.out::println);

        userService.cleanUsersTable();

        userService.dropUsersTable();

    }
}
