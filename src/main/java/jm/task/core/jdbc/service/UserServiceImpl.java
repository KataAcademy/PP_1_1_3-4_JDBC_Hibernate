package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl extends Util implements UserService {
    UserDaoJDBCImpl userDaoJDBCIml = new UserDaoJDBCImpl();

    public void createUsersTable() throws SQLException {
        userDaoJDBCIml.createUsersTable();
    }

    public void dropUsersTable() throws SQLException {
        userDaoJDBCIml.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        userDaoJDBCIml.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) throws SQLException {
        userDaoJDBCIml.removeUserById(id);
    }

    public List<User> getAllUsers() throws SQLException {
        return userDaoJDBCIml.getAllUsers();
    }

    public void cleanUsersTable() {
        userDaoJDBCIml.cleanUsersTable();
    }
}
