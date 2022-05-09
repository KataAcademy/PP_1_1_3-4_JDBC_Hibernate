package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionBuilder;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

//import java.lang.module.Configuration;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util<sessione> {
    /*
    Создадим объект Connection
    Создадим переменные для данных нашей БД созданной в mySQL Workbench.
    После создаем метод getBdConnection() для подключения к нашей БД.
    */

    // Создадим объект Connection

    static Connection bdConnection;

    /*
    Все данные берем из нашей БД которые можно посмотреть во вкладке Session окна Information
    Имя указываем то которое мы создали коммандой [CREATE DATABASE -имя-базы-данных-];
     */

    // Хост
    private static final String dbHost = "localhost";

    // Порт
    private static final String dbPort = "3306";

    // Имя базы данных
    private static final String dbName = "store";

    // Имя пользователя
    private static final String dbUser = "root";

    // Пароль пользователя
    private static final String dbPass = "172440";

    // Собираем URL нашей базы
    private static final String dbURL ="jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;

    /*
    Метод getBdConnection() которым мы будем устанавливать соединение может выбросить исключения:
    - ClassNotFoundException в случае отсутствия драйвера JDBC;
    - SQLException в случае проблем с соединением с нашей базой;
     */
    public static Connection getBdConnection(){
        // Указываем драйвер до установки соединения
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // Создаем наше подключение и не забываем про возможное SQLException.
        try {
            bdConnection = DriverManager.getConnection(dbURL, dbUser, dbPass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bdConnection;
    }


    public static SessionFactory getSessionFactory() throws HibernateException {
        // Создадим объект Configuration для настройки нашего подключения
        Configuration configuration = new Configuration();
        ServiceRegistry serviceRegistry = null;

        try {
            configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
            configuration.setProperty("hibernate.connection.url", dbURL);
            configuration.setProperty("hibernate.connection.username", dbUser);
            configuration.setProperty("hibernate.connection.password", dbPass);
            configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
            configuration.setProperty("hibernate.show_sql", "true");
            configuration.setProperty("hibernate.current_session_context_class", "thread");
            configuration.addAnnotatedClass(User.class);

            serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .build();
        } catch(HibernateException exception){
            System.out.println("----------------> Problem creating session factory <----------------");
            exception.printStackTrace();
        }
        return configuration.buildSessionFactory(serviceRegistry);
    }
}
