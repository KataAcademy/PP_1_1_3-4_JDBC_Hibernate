package jm.task.core.jdbc.util;
import jm.task.core.jdbc.model.User;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
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
                hibernateConfig.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
                hibernateConfig.setProperty("hibernate.connection.url", URL);
                hibernateConfig.setProperty("hibernate.connection.username", USER_NAME);
                hibernateConfig.setProperty("hibernate.connection.password", PWD);
                hibernateConfig.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
                hibernateConfig.addAnnotatedClass(User.class);
                sessionFactory = hibernateConfig.buildSessionFactory(new StandardServiceRegistryBuilder()
                        .applySettings(hibernateConfig.getProperties()).build());
            } catch (HibernateException e) {
                throw new RuntimeException(e);
            }
//            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//            Session session = sessionFactory.openSession();
        }
        return sessionFactory;
    }
}
