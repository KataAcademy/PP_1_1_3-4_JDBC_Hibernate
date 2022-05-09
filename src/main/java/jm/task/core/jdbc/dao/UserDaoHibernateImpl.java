package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private Session session;


    public UserDaoHibernateImpl() {
        Util util = new Util();
        session = util.getSessionFactory().openSession();
    }


    @Override
    public void createUsersTable() {
        session.beginTransaction();
        session.createSQLQuery("CREATE TABLE IF NOT EXISTS users" +
                "(id BIGINT PRIMARY KEY AUTO_INCREMENT," +
                "name VARCHAR (10)," +
                "lastName VARCHAR (20)," +
                "age TINYINT)").executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public void dropUsersTable() {
        session.beginTransaction();
        session.createSQLQuery("DROP TABLE IF EXISTS users").addEntity(User.class);
        session.getTransaction().commit();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        session.beginTransaction();
        session.save(new User(name, lastName, age));
        session.getTransaction().commit();
    }

    @Override
    public void removeUserById(long id) {
        session.beginTransaction();
        session.delete(session.get(User.class, id));
        session.getTransaction().commit();
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = session.createQuery("from User").getResultList();
        System.out.println("All users -> {" + userList + "}");
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        session.beginTransaction();
        session.createQuery("delete User").executeUpdate();
        session.getTransaction().commit();
    }
}
