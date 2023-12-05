package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.*;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        Util.getConnection();
        UserDao userDao = new UserDaoJDBCImpl();

        userDao.createUsersTable();

        userDao.saveUser("Krot", "Smit", (byte) 20);
        userDao.saveUser("Zebra", "Zebrovski", (byte) 25);
        userDao.saveUser("Pikachu", "ChuChu", (byte) 31);
        userDao.saveUser("Man", "Last", (byte) 38);

        userDao.removeUserById(1);
        userDao.getAllUsers().forEach(System.out::println);
        userDao.cleanUsersTable();
        userDao.dropUsersTable();

    }
}
