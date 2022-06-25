package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    Transaction transaction = null;


    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try(Session session = Util.getSession().openSession()) { // sessionFactory - устарел?
       // start a transaction
            transaction = session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS users(" +
                    "id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY NOT NULL," +
                    "name VARCHAR(30)," +
                    "lastName VARCHAR(30)," +
                    "age TINYINT);").executeUpdate();
            session.getTransaction().commit();

        }
    }
    @Override
    public void dropUsersTable() {

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getSession().openSession()) {
            transaction = session.beginTransaction();
            User user = new User(name, lastName, age); // сощдаем новый объект юзер с параметрами которые приняли
            System.out.println(user);
            session.save(user);
            System.out.println(user);
            session.getTransaction().commit(); // коммитим то, что сделали

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

        @Override
    public void removeUserById(long id) {
            try (Session session = Util.getSession().openSession()) {

            } catch (Exception e) {
                e.printStackTrace();
            }

    }

    @Override
    public List<User> getAllUsers() {
        List <User> userList;
//        try (Session session = Util.getSession().openSession()) { // все операции выполняются в рамках отдельной сессии
//            session.beginTransaction(); // любая операция операция баз данных должна выполняться в рамках транзакции
//            userList = session.createQuery("SELECT  from User", User.class).list();
//            session.getTransaction().commit();
            return null;
//        }


    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSession().openSession()) {

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
