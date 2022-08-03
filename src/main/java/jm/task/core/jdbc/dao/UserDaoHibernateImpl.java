package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;
import static jm.task.core.jdbc.util.Util.getSessionFactory;


public class UserDaoHibernateImpl implements UserDao {
    private String dropUserTable = "drop table if exists User;\n";
    private String createUsersTable = "create table if not exists " +
            "User \n" +
            "(\n" +
            "\tid bigint auto_increment primary key, \n" +
            "\t name varchar(20), \n" +
            " lastName varchar(20),\n" +
            "\t age tinyint\n" +
            ");";
    private SessionFactory sessionFactory = getSessionFactory();
    private Session session;
    private Transaction transaction;

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        try {
            session = sessionFactory.getCurrentSession();
            transaction = session.beginTransaction();
            session.createSQLQuery(createUsersTable).executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
        }

    }

    @Override
    public void dropUsersTable() {
        try {
            session = sessionFactory.getCurrentSession();
            transaction = session.beginTransaction();
            session.createSQLQuery(dropUserTable).executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
        session = sessionFactory.getCurrentSession();
        transaction =session.beginTransaction();
        User user = new User( name, lastName, age) ;
        session.saveOrUpdate(user);
        transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
        }

    }

    @Override
    public void removeUserById(long id) {
        try {
        session = sessionFactory.getCurrentSession();
        transaction = session.beginTransaction();
        User user = session.get(User.class, id );
        if (user != null){
            session.delete(user);
        }
        transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
        }

    }

    @Override
    public List<User> getAllUsers() {
        try {
        session = sessionFactory.getCurrentSession();
        transaction = session.beginTransaction();
        List <User> listUsers = session.createQuery("select i from User i",User.class).getResultList();
        transaction.commit();
        return listUsers;
        } catch (HibernateException e) {
            transaction.rollback();
            return null;
        }
    }

    @Override
    public void cleanUsersTable() {
        try {
        session = sessionFactory.getCurrentSession();
        transaction = session.beginTransaction();
        session.createSQLQuery("delete from User").executeUpdate();
        transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
        }
    }
}
