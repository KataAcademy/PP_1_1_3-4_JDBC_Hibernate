package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/test";
    private static final String USER_NAME = "ususer";
    private static final String PWD = "UD@~VX%uDOpBF7";

    public static Connection getConnectionToDB() {
        // реализуйте настройку соеденения с БД
        try {
            return DriverManager.getConnection(URL, USER_NAME, PWD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration hibernateConfig = new Configuration();
                //set hibernate properties
                hibernateConfig.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
                hibernateConfig.setProperty("hibernate.connection.url", URL);
                hibernateConfig.setProperty("hibernate.connection.username", USER_NAME);
                hibernateConfig.setProperty("hibernate.connection.password", PWD);
                hibernateConfig.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
                hibernateConfig.setProperty("hibernate.show_sql", "true");
                hibernateConfig.setProperty("hibernate.hbm2ddl", "update");

                //set C3PO properties
                hibernateConfig.setProperty("hibernate.connection.provider_class",
                        "org.hibernate.connection.C3P0ConnectionProvider");
                hibernateConfig.setProperty(Environment.C3P0_MIN_SIZE, "5");
                hibernateConfig.setProperty(Environment.C3P0_MAX_SIZE, "10");
                hibernateConfig.setProperty(Environment.C3P0_TIMEOUT, "1800");
                hibernateConfig.setProperty(Environment.C3P0_MAX_STATEMENTS, "50");
                hibernateConfig.setProperty(Environment.C3P0_IDLE_TEST_PERIOD, "300");
                //annotation class
                hibernateConfig.addAnnotatedClass(User.class);
                //build sessionFactory
                sessionFactory = hibernateConfig.buildSessionFactory(new StandardServiceRegistryBuilder()
                        .applySettings(hibernateConfig.getProperties()).build());
            } catch (HibernateException e) {
                throw new RuntimeException(e);
            }
        }
        return sessionFactory;
    }
}
