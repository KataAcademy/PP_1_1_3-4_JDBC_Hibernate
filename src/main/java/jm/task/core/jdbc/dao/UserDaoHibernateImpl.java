package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private Session session;
    private final SessionFactory sessionFactory;
    private String tableName = "User";
    private String createTable = "CREATE TABLE IF NOT EXISTS " + tableName +
            "(id BIGINT PRIMARY KEY AUTO_INCREMENT, " +
            "name VARCHAR(30), lastName VARCHAR(30), age TINYINT)";
    private String dropTable = "DROP TABLE IF EXISTS " + tableName;

    public UserDaoHibernateImpl() {
        sessionFactory = Util.getSessionFactory();
    }


    @Override
    public void createUsersTable() {
        try {
            System.out.println("Попытка создания таблицы c именем: \"" + tableName + "\"");
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.createSQLQuery(createTable).executeUpdate();
            session.getTransaction().commit();
            System.out.println("Таблица c именем: \"" + tableName + "\" создана!\n");
        } catch (Exception e) {
            rollBack();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void dropUsersTable() {
        try {
            System.out.println("Попытка удаления таблицы c именем: \"" + tableName + "\"");
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.createSQLQuery(dropTable).executeUpdate();
            session.getTransaction().commit();
            System.out.println("Таблица c именем: \"" + tableName + "\" удалена!\n");
        } catch (Exception e) {
            rollBack();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            User user = new User(name, lastName, age);
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            System.out.println("Пользователь " + "\"" + user.getName() + "\"" + " добавлен!");
        } catch (Exception e) {
            rollBack();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


    @Override
    public void removeUserById(long id) {
        try {
            System.out.println("Попытка удаления из таблицы: \"" + tableName + "\" пользователя с id: " + id);
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            session.getTransaction().commit();
            System.out.println("Пользователь с id: \"" + id + "\" удален из таблицы:" + "\"" + tableName + "\"\n");
        } catch (Exception e) {
            rollBack();
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {

        try {

            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            List<User> users = session.createQuery("FROM " + tableName, User.class).getResultList();
            for (User u : users) {
                System.out.println("\n" + u + "\n______________________________________________");
            }
            return users;
        } finally {
            session.close();
        }
    }

    @Override
    public void cleanUsersTable() {
        try {
            System.out.println("Очищение содержимого таблицы..........");
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.createQuery("DELETE " + tableName).executeUpdate();
            session.getTransaction().commit();
            System.out.println("Содержимое таблицы очищено!");
        } catch (Exception e) {
            rollBack();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void rollBack() {
        try {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
