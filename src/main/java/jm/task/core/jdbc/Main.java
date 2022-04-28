package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        // Подключение
        Util.getBdConnection();
        // Создаем экземпляр класса где мы описали все наши методы
        UserDao userDao = new UserDaoJDBCImpl();
        // Создание таблицы для User(ов) – не должно приводить к исключению, если такая таблица уже существует
        userDao.createUsersTable();
        // Добавление User в таблицу
        userDao.saveUser("Name1", "LastName1", (byte) 20);
        userDao.saveUser("Name2", "LastName2", (byte) 25);
        userDao.saveUser("Name3", "LastName3", (byte) 31);
        userDao.saveUser("Name4", "LastName4", (byte) 38);
        // Удаление User из таблицы ( по id )
        userDao.removeUserById(1);
        // Получение всех User(ов) из таблицы
        userDao.getAllUsers();
        // Очистка содержания таблицы
        userDao.cleanUsersTable();
        // Удаление таблицы User(ов) – не должно приводить к исключению, если таблицы не существует
        userDao.dropUsersTable();
    }
}
