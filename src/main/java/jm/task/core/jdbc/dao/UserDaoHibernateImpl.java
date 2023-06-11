package jm.task.core.jdbc.dao;

import com.mysql.cj.SimpleQuery;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;


import javax.management.Query;
import java.sql.SQLException;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            SQLQuery sqlQuery = session.createSQLQuery("CREATE TABLE IF NOT EXISTS user " +
                    "(id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(45), lastName VARCHAR(45), age TINYINT)");
            sqlQuery.executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            SQLQuery sqlQuery = session.createSQLQuery("DROP TABLE IF EXISTS user")
                    .addEntity(User.class);
            sqlQuery.executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {


        try (Session session = Util.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            User user = new User();
            user.setAge(age);
            user.setName(name);
            user.setLastName(lastName);
            session.persist(user);
            session.getTransaction().commit();

            }
        }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createQuery("delete User where id = id").executeUpdate();
            transaction.commit();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users;
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            users = session.createQuery("from User").getResultList();
//            SQLQuery query = session.createSQLQuery("SELECT * FROM user");
//            query.addEntity(User.class);
//            users = query.list();
            session.getTransaction().commit();

        }
        return users;
//        if (users!= null) {
//            return users;
//        }
//        return null;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            SQLQuery sqlQuery = session.createSQLQuery("TRUNCATE user");
            sqlQuery.executeUpdate();
            session.getTransaction().commit();
        }
    }
}
