package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.exception.DaoException;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private static final String CREATE_TABLE_SQL = """
            CREATE TABLE IF NOT EXISTS user (
                id BIGINT PRIMARY KEY AUTO_INCREMENT,
                name VARCHAR(32) NOT NULL,
                last_name VARCHAR(32) NOT NULL,
                age TINYINT NOT NULL
            );
            """;

    private static final String DROP_TABLE_SQL = """
            DROP TABLE IF EXISTS user;
            """;

    private static final SessionFactory sessionFactory = Util.getSessionFactory();

    private static Transaction transaction = null;

    public UserDaoHibernateImpl() {
    }

    private static Session getSession() {
        return sessionFactory.openSession();
    }

    @Override
    public void createUsersTable() {
        createOrDropTable(CREATE_TABLE_SQL);
    }

    @Override
    public void dropUsersTable() {
        createOrDropTable(DROP_TABLE_SQL);
    }

    private void createOrDropTable(String sql) {
        try (var session = getSession()) {
            transaction = session.beginTransaction();

            session.createNativeQuery(sql).executeUpdate();

            transaction.commit();
        } catch (Exception throwables) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DaoException(throwables);
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (var session = getSession()) {
            transaction = session.beginTransaction();

            session.save(User.builder()
                    .name(name)
                    .lastName(lastName)
                    .age(age)
                    .build());

            transaction.commit();

            System.out.println("User с именем - " + name + " " + lastName + " добавлен в базу данных");
        } catch (Exception throwables) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DaoException(throwables);
        }
    }

    @Override
    public void removeUserById(long id) {
        try (var session = getSession()) {
            transaction = session.beginTransaction();

            session.delete(session.get(User.class, id));

            transaction.commit();
        } catch (Exception throwables) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DaoException(throwables);
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (var session = getSession()) {
            var cb = session.getCriteriaBuilder();

            var criteria = cb.createQuery(User.class);
            var user = criteria.from(User.class);
            criteria.select(user);

            return session.createQuery(criteria).list();
        } catch (Exception throwables) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DaoException(throwables);
        }
    }

    @Override
    public void cleanUsersTable() {
        try (var session = getSession()) {
            transaction = session.beginTransaction();

            session.createQuery("DELETE FROM " + User.class.getSimpleName()).executeUpdate();

            transaction.commit();
        } catch (Exception throwables) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DaoException(throwables);
        }
    }
}
