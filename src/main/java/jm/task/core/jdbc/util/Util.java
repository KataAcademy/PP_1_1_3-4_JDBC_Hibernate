package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
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
        // Собираем URL нашей базы
        String dbURL ="jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
        // Создаем наше подключение и не забываем про возможное SQLException.
        try {
            bdConnection = DriverManager.getConnection(dbURL, dbUser, dbPass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bdConnection;
    }

}
