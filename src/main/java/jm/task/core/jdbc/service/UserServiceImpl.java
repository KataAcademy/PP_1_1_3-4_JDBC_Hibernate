package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDaoJDBCImpl USER_DAO_JDBC = new UserDaoJDBCImpl();

    @Override
    public void createUsersTable() {
        USER_DAO_JDBC.createUsersTable();
    }

    @Override
    public void dropUsersTable() {
        USER_DAO_JDBC.dropUsersTable();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        USER_DAO_JDBC.saveUser(name, lastName, age);
    }

    @Override
    public void removeUserById(long id) {
        USER_DAO_JDBC.removeUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return USER_DAO_JDBC.getAllUsers();
    }

    @Override
    public void cleanUsersTable() {
        USER_DAO_JDBC.cleanUsersTable();
    }
}
