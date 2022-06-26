package jm.task.core.jdbc.util;

import com.fasterxml.classmate.AnnotationConfiguration;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import javax.imageio.spi.ServiceRegistry;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static Connection connection;
    private static SessionFactory sessionFactory;
    public static String driver = "com.mysql.cj.jdbc.Driver";
    // реализуйте настройку соеденения с БД

    public static Connection getConnection() {
        try {
            Class.forName(driver);
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static SessionFactory getSession() {
        if (sessionFactory == null) {
                Configuration configuration = new Configuration();
// настройки, вместо xml
                Properties properties = new Properties();
                properties.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                properties.put(Environment.URL, URL);
                properties.put(Environment.USER, USERNAME);
                properties.put(Environment.PASS, PASSWORD);

                properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
                properties.put(Environment.SHOW_SQL, "true");
                properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                properties.put(Environment.HBM2DDL_AUTO, "update");
                configuration.setProperties(properties);
                configuration.addAnnotatedClass(User.class);
//                StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
//                        .applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory();
//            properties.setProperty("hibernate.connection.url", URL);
//            properties.setProperty("hibernate.connection.username", USERNAME);
//            properties.setProperty("hibernate.connection.password", PASSWORD);
//            properties.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");
//            properties.put("show_sql", true);
//
//            configuration.setProperties(properties)
//                    .addAnnotatedClass(User.class);
//            sessionFactory = configuration.buildSessionFactory();


        }
    return sessionFactory;
    }
}

