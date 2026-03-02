package jm.task.core.jdbc.service;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import java.util.List;

public class UserServiceImpl implements UserService {
    public void createUsersTable() {
        UserDaoHibernateImpl userDao = new UserDaoHibernateImpl();
        userDao.createUsersTable();
    }

    public void dropUsersTable() {
        UserDaoHibernateImpl userDao = new UserDaoHibernateImpl();
        userDao.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        UserDaoHibernateImpl userDao = new UserDaoHibernateImpl();
        userDao.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        UserDaoHibernateImpl userDao = new UserDaoHibernateImpl();
        userDao.removeUserById(id);
    }

    public List<User> getAllUsers() {
        UserDaoHibernateImpl userDao = new UserDaoHibernateImpl();
        return userDao.getAllUsers();
    }

    public void cleanUsersTable() {
        UserDaoHibernateImpl userDao = new UserDaoHibernateImpl();
        userDao.cleanUsersTable();
    }
}
