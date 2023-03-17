package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();

    @Override
    public void createUsersTable() {
        userDaoJDBC.createUsersTable();
    }

    @Override
    public void dropUsersTable() {
        userDaoJDBC.dropUsersTable();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        userDaoJDBC.saveUser(name, lastName, age);
    }

    @Override
    public void removeUserById(long id) {
        userDaoJDBC.removeUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDaoJDBC.getAllUsers();
    }

    @Override
    public void cleanUsersTable() {
        userDaoJDBC.cleanUsersTable();
    }
}
