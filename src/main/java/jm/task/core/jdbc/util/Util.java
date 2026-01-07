package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.Properties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/kata_db";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "<SHI-e21h180w75>";
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";

    private static SessionFactory sessionFactory;

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            System.out.println("Соединение с базой данных установлено!");
        } catch (ClassNotFoundException e) {
            System.err.println("Ошибка: Не удалось загрузить драйвер базы данных!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Ошибка: Не удалось подключиться к базе данных!");
            System.err.println("Проверьте: 1) Запущен ли MySQL сервер 2) Правильность URL, имени и пароля");
            e.printStackTrace();
        }
        return connection;
    }
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                Properties properties = new Properties();
                properties.put(Environment.DRIVER, DB_DRIVER);
                properties.put(Environment.URL, DB_URL);
                properties.put(Environment.USER, DB_USERNAME);
                properties.put(Environment.PASS, DB_PASSWORD);
                properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
                properties.put(Environment.SHOW_SQL, "true");

                configuration.setProperties(properties);
                configuration.addAnnotatedClass(User.class);

                sessionFactory = configuration.buildSessionFactory();
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Ошибка создания SessionFactory");
            }
        }
        return sessionFactory;
    }
}



