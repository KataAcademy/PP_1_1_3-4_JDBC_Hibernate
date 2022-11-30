package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private final static String URL = "jdbc:mysql://localhost:3306/MyDBKata";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "rootroot";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connection true");
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Connection false");
        }
        return connection;
    }
}
