package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.management.Query;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getConnection;


public class UserDaoHibernateImpl implements UserDao {
    private String createUsersTable = "create table if not exists " +
            "User \n" +
            "(\n" +
            "\tid bigint auto_increment primary key, \n" +
            "\t name varchar(20), \n" +
            " lastName varchar(20),\n" +
            "\t age tinyint\n" +
            ");";
    private String dropUserTable = "drop table if exists User;\n";
    private SessionFactory sessionFactory = getConnection();
    Session session;
    Transaction transaction;
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        session = sessionFactory.getCurrentSession();
        transaction = session.beginTransaction();
        session.createSQLQuery(createUsersTable).executeUpdate();
        transaction.commit();

    }

    @Override
    public void dropUsersTable() {
        session = sessionFactory.getCurrentSession();
        transaction = session.beginTransaction();
        session.createSQLQuery(dropUserTable).executeUpdate();
        transaction.commit();

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        session = sessionFactory.getCurrentSession();
        transaction =session.beginTransaction();
        User user = new User( name, lastName, age) ;
        session.saveOrUpdate(user);
        transaction.commit();

    }

    @Override
    public void removeUserById(long id) {
        session = sessionFactory.getCurrentSession();
        transaction = session.beginTransaction();
        User user = session.get(User.class, id );
        if (user != null){
            session.delete(user);
        }
        transaction.commit();

    }

    @Override
    public List<User> getAllUsers() {
        session = sessionFactory.getCurrentSession();
        transaction = session.beginTransaction();
        List <User> listUsers = session.createQuery("select i from User i",User.class).getResultList();
        transaction.commit();
        return listUsers;
    }

    @Override
    public void cleanUsersTable() {
        session = sessionFactory.getCurrentSession();
        transaction = session.beginTransaction();
        session.createSQLQuery("delete from User");
        transaction.commit();

    }
}
