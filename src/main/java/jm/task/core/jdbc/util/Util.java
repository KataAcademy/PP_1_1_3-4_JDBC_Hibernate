package jm.task.core.jdbc.util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import jm.task.core.jdbc.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

public class Util {
    // реализуйте настройку соеденения с БД
    public static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://localhost:3306/kataschema";
    public static final String DB_USER = "root";
    public static final String DB_PASSWORD = "&J(dRp!EF(8m";

    public static Connection getConnection() {
        Connection con;
        try {
            Class.forName(DB_DRIVER);
            con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return con;
    }

    public static SessionFactory getSessionFactory() {
        SessionFactory sessionFactory = null;
        try {
            Configuration config = new Configuration();

            config.setProperties(getProperties());
            config.addAnnotatedClass(User.class);

            sessionFactory = config.buildSessionFactory();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return sessionFactory;
    }

    private static Properties getProperties() {
        Properties props = new Properties();

        props.put(Environment.DRIVER, DB_DRIVER);
        props.put(Environment.URL, DB_URL);
        props.put(Environment.USER, DB_USER);
        props.put(Environment.PASS, DB_PASSWORD);
        props.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
        props.put(Environment.DEFAULT_SCHEMA, "kataschema");
        props.put(Environment.SHOW_SQL, "true");
        props.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        return props;
    }
}
