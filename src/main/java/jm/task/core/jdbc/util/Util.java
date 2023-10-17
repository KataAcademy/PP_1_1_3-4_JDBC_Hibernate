package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static final String URLFIXED =
            "jdbc:mysql://localhost:3306/ppbd?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123";

    private static SessionFactory sessionFactory;

    public static Connection getConnection() {
        Connection con;
        try {
            con = DriverManager.getConnection(URLFIXED, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return con;
    }



//    public static SessionFactory getSessionFactory() {
//        if (sessionFactory == null) {
//            try {
//                Configuration config = getConfiguration();
//                config.addAnnotatedClass(User.class);
//                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
//                        .applySettings(config.getProperties()).build();
//                sessionFactory = config.buildSessionFactory(serviceRegistry);
//                System.out.println("sessionFactory ok");
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//            finally {
//                assert sessionFactory != null;
//                sessionFactory.close();
//            }
//        }
//        return sessionFactory;
//    }
//
//    private static Configuration getConfiguration() {
//        Configuration config = new Configuration();
//
//        Properties settings = new Properties();
////        settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
//        settings.put(Environment.URL, URLFIXED);
//        settings.put(Environment.USER, USERNAME);
//        settings.put(Environment.PASS, PASSWORD);
//        settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
//
//        settings.put(Environment.SHOW_SQL, "true");
//
//        settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
//
//        settings.put(Environment.HBM2DDL_AUTO, "");
//
//        config.setProperties(settings);
//        return config;
//    }
}

