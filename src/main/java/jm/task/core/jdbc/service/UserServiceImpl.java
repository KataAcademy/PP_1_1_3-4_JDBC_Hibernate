package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDaoHibernateImpl USER_DAO_HIBERNATE = new UserDaoHibernateImpl();

    @Override
    public void createUsersTable() {
        USER_DAO_HIBERNATE.createUsersTable();
    }

    @Override
    public void dropUsersTable() {
        USER_DAO_HIBERNATE.dropUsersTable();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        USER_DAO_HIBERNATE.saveUser(name, lastName, age);
    }

    @Override
    public void removeUserById(long id) {
        USER_DAO_HIBERNATE.removeUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return USER_DAO_HIBERNATE.getAllUsers();
    }

    @Override
    public void cleanUsersTable() {
        USER_DAO_HIBERNATE.cleanUsersTable();
    }
}
