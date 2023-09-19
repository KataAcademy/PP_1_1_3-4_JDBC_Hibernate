package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    static final SessionFactory sessionFactory = Util.getSessionFactory();
    Session session = null;
    public UserDaoHibernateImpl() {

    }
    @Override
    public void createUsersTable() {
        try {
            String query = "CREATE TABLE IF NOT EXISTS User" +
                    "(" +
                    "id BIGINT PRIMARY KEY AUTO_INCREMENT," +
                    "name TEXT," +
                    "lastName TEXT," +
                    "age TINYINT" +
                    ")";
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.createSQLQuery(query).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public void dropUsersTable() {
        try {
            String query = "DROP TABLE IF EXISTS User";
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.createSQLQuery(query).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            User user = new User();
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            user.setName(name);
            user.setLastName(lastName);
            user.setAge(age);
            session.persist(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.createQuery("DELETE User where id = id").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = null;
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            users =  session.createQuery("FROM User", User.class).getResultList();
            transaction.commit();
            return users;
        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.createSQLQuery("TRUNCATE user").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
