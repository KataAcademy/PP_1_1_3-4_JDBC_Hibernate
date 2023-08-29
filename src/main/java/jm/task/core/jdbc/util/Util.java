package jm.task.core.jdbc.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/1_1_4_Kata";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1234";

    private static Connection connection = null;

    public Connection getConnection() {
        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connection OK");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Connected ERROR");
        }
        return connection;
    }
    public static SessionFactory getHibernateSessionFactory() {
        Configuration configuration = new Configuration();

        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/1_1_4_Kata?useSSL=false&serverTimezone=UTC");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        configuration.setProperty("hibernate.connection.username", "root");
        configuration.setProperty("hibernate.connection.password", "1234");
        configuration.setProperty("hibernate.current_session_context_class", "thread");
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        configuration.setProperty("hibernate.show_sql", "true");

        configuration.addAnnotatedClass(jm.task.core.jdbc.model.User.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        return sessionFactory;
    }

}
