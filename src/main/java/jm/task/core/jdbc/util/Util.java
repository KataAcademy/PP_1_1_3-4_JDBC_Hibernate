package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String URL = "jdbc:mysql://localhost:3306/mydb_kata";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Koncurrent2033";

    public static Connection connection;
    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Соединение с БД установлено");
        } catch (SQLException ex) {
            System.err.println("Не удалось подключиться");
            ex.printStackTrace();
        }
        return connection;
    }

}
