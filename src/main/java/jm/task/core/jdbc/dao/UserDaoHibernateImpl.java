package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        //реализация через SQL запрос
        String sql = """
                CREATE TABLE IF NOT EXISTS User\s
                (
                'id' BIGINT PRIMARY KEY auto_increment,\s
                name TEXT,\s
                lastName TEXT,\s
                age TINYINT
                );
                """;
        execute(sql, "Table 'User' has been created.");

    }

    @Override
    public void dropUsersTable() {
        //реализация через SQL запрос
        String sql = """
                DROP TABLE IF EXISTS User;
                """;
        execute(sql, "Table 'User' has been deleted.");
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        createUsersTable();
        String sql = String
                .format("INSERT INTO User (name, lastName, age) VALUES (%s, %s, %d)",
                        name, lastName, age);
        execute(sql, "User " + name + " " + lastName + " has been added to 'User'");
    }

    @Override
    public void removeUserById(long id) {
        String sql = String
                .format("DELETE IGNORE FROM User WHERE id = %d", id);
        execute(sql, "User with id " + id + " has been removed from the table.");
    }

    @Override
    public List<User> getAllUsers() {
        SessionFactory sessionFactory = Util.getFactory();
        try {
            String sql = "SELECT * FROM User";
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            List<User> users = session.createNativeQuery(sql, User.class).getResultList();
            session.getTransaction().commit();
            return users;
        } finally {
            sessionFactory.close();
        }
    }

    @Override
    public void cleanUsersTable() {
        String sql = """
                TRUNCATE TABLE User;
                """;

    }
    public void execute(String sql, String sqlMessage) {
        SessionFactory sessionFactory = Util.getFactory();
        try {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            System.out.println(sqlMessage);
            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
    }
}
