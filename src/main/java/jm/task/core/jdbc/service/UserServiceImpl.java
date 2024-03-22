package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

import static java.util.logging.Logger.getLogger;
import java.util.logging.Logger;

public class UserServiceImpl implements UserService {
    UserDaoJDBCImpl dao = new UserDaoJDBCImpl();
    public void createUsersTable() {
        dao.createUsersTable();
    }

    public void dropUsersTable() {
        dao.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        dao.saveUser(name, lastName, age);
        Logger logger = getLogger(User.class.getName());
        String message = String.format("User с именем — %s добавлен в базу данных", name);
        logger.info(message);
    }

    public void removeUserById(long id) {
        dao.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return dao.getAllUsers();
    }

    public void cleanUsersTable() {
        dao.dropUsersTable();
    }
}
