package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();

    public void createUsersTable() {
        userDaoJDBC.createUsersTable();
        System.out.println("Таблица users была создана.");
    }

    public void dropUsersTable() {
        userDaoJDBC.dropUsersTable();
        System.out.println("Таблица users была удалена.");
    }

    public void saveUser(String name, String lastName, byte age) {
        userDaoJDBC.saveUser(name, lastName, age);
        System.out.println("User с именем " + name + " добавлен в базу данных.");
    }

    public void removeUserById(long id) {
        if (getUserById(id) != null) {
            userDaoJDBC.removeUserById(id);
            System.out.println("Пользователь с id=" + id + " удалён из базы данных.");
        }
    }

    public List<User> getAllUsers() {
        return userDaoJDBC.getAllUsers();
    }

    public User getUserById(long id) {
        if (userDaoJDBC.getUserById(id) != null) {
            return userDaoJDBC.getUserById(id);
        } else {
            System.err.println("Пользователя с id=" + id + " не существует.");
            return null;
        }
    }

    public void cleanUsersTable() {
        userDaoJDBC.cleanUsersTable();
    }

}
