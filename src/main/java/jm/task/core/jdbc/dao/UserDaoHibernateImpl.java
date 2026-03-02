package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {
    }


    @Override
    public void createUsersTable() {
        String sql = """
                CREATE TABLE IF NOT EXISTS Users (
                    id SERIAL PRIMARY KEY,
                    name varchar(255) NOT NULL,
                    lastname varchar(255) NOT NULL,
                    age smallint(99) NOT NULL);
                """;

        Transaction tx = null;

        try (Session session = Util.getSessionFactory().openSession()) {
            tx = session.beginTransaction();

            session.createNativeQuery(sql, User.class).executeUpdate();

            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw (e);
        }
    }

    @Override
    public void dropUsersTable() {
        String sql = """
                DROP TABLE IF EXISTS Users;
                """;

        Transaction tx = null;

        try (Session session = Util.getSessionFactory().openSession()) {
            tx = session.beginTransaction();

            session.createNativeQuery(sql, User.class).executeUpdate();

            tx.commit();

        } catch (RuntimeException e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw (e);
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = User.builder()
                .name(name)
                .lastName(lastName)
                .age(age)
                .build();

        Transaction tx = null;

        try (Session session = Util.getSessionFactory().openSession()) {
            tx = session.beginTransaction();

            session.persist(user);

            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw (e);
        }
    }

    @Override
    public void removeUserById(long id) {

        Transaction tx = null;

        try (Session session = Util.getSessionFactory().openSession()) {
            tx = session.beginTransaction();

            User user = session.find(User.class, id);
            session.remove(user);

            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw (e);
        }
    }

    @Override
    public List<User> getAllUsers() {

        List<User> users = new ArrayList<>();

        String sql = """
                SELECT * FROM Users;
                """;

        Transaction tx = null;

        try (Session session = Util.getSessionFactory().openSession()) {
            tx = session.beginTransaction();

            users = session.createNativeQuery(sql, User.class).getResultList();

            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw (e);
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        String sql = """
                TRUNCATE TABLE Users;
                """;

        Transaction tx = null;

        try (Session session = Util.getSessionFactory().openSession()) {
            tx = session.beginTransaction();

            session.createNativeQuery(sql, User.class).executeUpdate();

            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw (e);
        }
    }
}
