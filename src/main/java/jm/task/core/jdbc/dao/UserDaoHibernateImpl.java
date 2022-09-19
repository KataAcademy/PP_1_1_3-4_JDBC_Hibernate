package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        String sqlQuery = "CREATE TABLE IF NOT EXISTS users (" +
                "id BIGINT PRIMARY KEY AUTO_INCREMENT, " +
                "name VARCHAR(255), " +
                "lastName VARCHAR(255), " +
                "age INT)";

        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createSQLQuery(sqlQuery).addEntity(User.class).executeUpdate();
            session.getTransaction().commit();
            System.out.println("Таблица создана");
        } catch (RuntimeException e) {
            System.out.println("Таблица НЕ создана!");
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        String sqlQuery = "DROP TABLE IF EXISTS users";
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createSQLQuery(sqlQuery).addEntity(User.class).executeUpdate();
            session.getTransaction().commit();
            System.out.println("Таблица удалена");
        } catch (RuntimeException e) {
            System.out.println("Таблица НЕ удалена!");
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            System.out.println("User " + name + " " + lastName + " добавлен"); // для индикации
        } catch (RuntimeException e) {
            System.out.println("User " + name + " " + lastName + " НЕ может быть добален в таблицу!");
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.delete(session.get(User.class, id));
            session.getTransaction().commit();
            System.out.println("User с ID = " + id + " удален");
        } catch (RuntimeException e) {
            System.out.println("User с ID = " + id + " НЕ может быть удален!");
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = Util.getSessionFactory().openSession()) {
            System.out.println("Данные из таблицы получены");
            return session.createQuery("FROM User").getResultList();
        }
    }

    @Override
    public void cleanUsersTable() {
        String sqlQuery = "DELETE FROM db_users.users";
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createSQLQuery(sqlQuery).addEntity(User.class).executeUpdate();
            session.getTransaction().commit();
            System.out.println("Таблица очищена");
        } catch (RuntimeException e) {
            System.out.println("Таблица не может быть очищена!");
            e.printStackTrace();
        }
    }
}