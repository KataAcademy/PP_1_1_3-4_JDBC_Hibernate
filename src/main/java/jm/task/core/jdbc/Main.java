package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserServiceImpl uService = new UserServiceImpl(); // исправила реализацию через UserDaoJDBCImpl на реализацию через UserServiceImpl

        // создаю таблицу
        uService.createUsersTable();

        // сохраняю юзеров в нее
        uService.saveUser("Nikita", "Petrov", (byte) 20);
        uService.saveUser("Denis", "Ivanov", (byte) 22);
        uService.saveUser("Olga", "Fedorova", (byte) 24);
        uService.saveUser("Marina", "Naumova", (byte) 26);

        uService.removeUserById(1); // удаляю по id 1
        uService.getAllUsers();
        uService.cleanUsersTable();
        uService.dropUsersTable();
    }
}
