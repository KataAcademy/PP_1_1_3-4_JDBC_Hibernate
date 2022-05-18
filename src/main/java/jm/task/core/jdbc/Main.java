package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        // Создаем экземпляр класса где мы описали все наши методы
        UserService userService = new UserServiceImpl();
        // Создание таблицы для User(ов) – не должно приводить к исключению, если такая таблица уже существует
        userService.createUsersTable();
        // Добавление User в таблицу
        userService.saveUser("Name1", "LastName1", (byte) 20);
        userService.saveUser("Name2", "LastName2", (byte) 25);
        userService.saveUser("Name3", "LastName3", (byte) 31);
        userService.saveUser("Name4", "LastName4", (byte) 38);
        // Удаление User из таблицы ( по id )
        userService.removeUserById(1);
        // Получение всех User(ов) из таблицы
        userService.getAllUsers();
        // Очистка содержания таблицы
        userService.cleanUsersTable();
        // Удаление таблицы User(ов) – не должно приводить к исключению, если таблицы не существует
        userService.dropUsersTable();
    }
}
