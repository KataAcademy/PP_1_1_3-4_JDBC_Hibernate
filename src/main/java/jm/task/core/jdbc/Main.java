package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

public class Main {
    public static void main(String[] args) {
        UserDao userDao = new UserDaoJDBCImpl();

        userDao.createUsersTable();

        userDao.saveUser("Alex", "Lion", (byte) 20);
        userDao.saveUser("Marti", "Zebra", (byte) 25);
        userDao.saveUser("Melman", "Giraffe", (byte) 31);
        userDao.saveUser("Gloria", "Hippo", (byte) 38);

        userDao.removeUserById(1);
        userDao.getAllUsers();
        userDao.cleanUsersTable();
        userDao.dropUsersTable();
    }
}
