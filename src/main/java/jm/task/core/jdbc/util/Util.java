package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    private Util() {
    }

    public static class MySql{
        private MySql(){

        }
        private static final String URL = "jdbc:mysql://localhost:3306/Kata";
        private static final String USERNAME = "root";
        private static final String PASSWORD = "05081968";

        // Нельзя создать обьект

        // public = Небезопасно, нужен DI, IOC.
        public static Connection getConnection() {
            Connection connection = null;
            try {
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            } catch (SQLException e) {
                System.err.println("Ошибка подключения к базе данных:");
                e.printStackTrace();
            }
            return connection;
        }
    }

    public static class Hibernate{
        private Hibernate(){

        }

        private final static Configuration configuration = new Configuration()
                .setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver")
                .setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/Kata")
                .setProperty("hibernate.connection.username", "root")
                .setProperty("hibernate.connection.password", "05081968")
                .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect")
                .setProperty("hibernate.show_sql", "true")
                .setProperty("hibernate.current_session_context_class", "thread")
                .addAnnotatedClass(User.class);
        private final static SessionFactory sessionFactory = configuration.buildSessionFactory();

        public static SessionFactory getSessionFactory() {
            return sessionFactory;
        }

    }
}
