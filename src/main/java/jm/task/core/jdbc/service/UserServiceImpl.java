package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    UserDao userDaoJDBC;
    public UserServiceImpl (UserDao userDao){
        this.userDaoJDBC = userDao;

    }

    public void createUsersTable() {
        this.userDaoJDBC.createUsersTable();
    }

    public void dropUsersTable() {
        this.userDaoJDBC.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        this.userDaoJDBC.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        this.userDaoJDBC.removeUserById(id);
    }

    public List<User> getAllUsers() {return this.userDaoJDBC.getAllUsers();
    }

    public void cleanUsersTable() {
        this.userDaoJDBC.cleanUsersTable();
    }
}
