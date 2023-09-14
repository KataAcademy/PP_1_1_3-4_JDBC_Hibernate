package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDaoJDBCImpl userDAO;
    public UserServiceImpl() {
        this.userDAO = new UserDaoJDBCImpl();
    }
    public void createUsersTable() {
        userDAO.createUsersTable();
    }

    public void dropUsersTable() {
        userDAO.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        userDAO.saveUser(user.getName(), user.getLastName(), user.getAge());
    }

    public void removeUserById(long id) {
        User user = new User();
        user.setId(id);
        userDAO.removeUserById(user.getId());
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public void cleanUsersTable() {
        userDAO.cleanUsersTable();
    }
}
