package jm.task.core.jdbc;

import jm.task.core.jdbc.exception.DaoException;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        var userService = new UserServiceImpl();

        var user1 = new User("Люк", "Скайуокер", (byte) 23);
        var user2 = new User("Квай-Гон", "Джинн", (byte) 47);
        var user3 = new User("Оби-Ван", "Кеноби", (byte) 38);
        var user4 = new User("Падме", "Амидала", (byte) 24);

        Transaction transaction = null;

        try (var session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            userService.createUsersTable();

            userService.saveUser(user1.getName(), user1.getLastName(), user1.getAge());
            userService.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
            userService.saveUser(user3.getName(), user3.getLastName(), user3.getAge());
            userService.saveUser(user4.getName(), user4.getLastName(), user4.getAge());

            userService.getAllUsers().forEach(System.out::println);

            userService.cleanUsersTable();

            userService.dropUsersTable();

            transaction.commit();
        } catch (Exception throwables) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}

