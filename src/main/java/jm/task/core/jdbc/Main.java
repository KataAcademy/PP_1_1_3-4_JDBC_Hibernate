package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserDao userDao = new UserDaoJDBCImpl();
        userDao.saveUser("Anton", "Sergeev", (byte) 25);
        userDao.saveUser("Sveta", "Sergeeva", (byte) 20);
        userDao.saveUser("Petr", "Petrov", (byte) 45);
        userDao.saveUser("Elena", "Alexeeva", (byte) 30);
    }
}
