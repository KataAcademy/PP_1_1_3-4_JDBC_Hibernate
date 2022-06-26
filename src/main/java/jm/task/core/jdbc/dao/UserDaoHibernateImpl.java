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
        try(Session session = Util.getSession().getCurrentSession()) { // sessionFactory - устарел?
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
        try(Session session = Util.getSession().getCurrentSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery("drop table mydb.users").executeUpdate();


            session.getTransaction().commit();
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getSession().getCurrentSession()) { // сессия - еденица работы
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
            try (Session session = Util.getSession().getCurrentSession()) { // метод getCurrentSession вызывает конструктор
                session.beginTransaction();
                User userFordb = session.get(User.class, id); // запрашиваю по айди
                session.remove(userFordb);
//                System.out.println(userFordb);
//                userFordb.setName("jopa");
//                session.flush(); // выталкивает запросы из контекста постоянства
//                User user = session.createQuery("select i from User i where i.id = :id", User.class).setParameter("id", id).getSingleResult(); // тоже самое, только через запрос
                session.getTransaction().commit();

            } catch (Exception e) {
                e.printStackTrace();
            }

    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = Util.getSession().getCurrentSession()) { // все операции выполняются в рамках отдельной сессии
            session.beginTransaction(); // любая операция операция баз данных должна выполняться в рамках транзакции
            List<User> userList = session.createQuery("select i from User i", User.class).getResultList();

            session.getTransaction().commit();
            return userList;
        }
    }

        @Override
        public void cleanUsersTable () {
            try (Session session = Util.getSession().getCurrentSession()) {
                session.beginTransaction();
                session.createSQLQuery("TRUNCATE table mydb.users");
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
