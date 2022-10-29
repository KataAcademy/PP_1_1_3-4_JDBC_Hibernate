package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {

    private static final String URL = "jdbc:mysql://localhost:3306/mydbtest";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "1234";
    private static Connection conn = null;

    public static Connection getConnection() {

        try {
            conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            System.out.println("Connection is successful");
        } catch (SQLException e) {
            System.out.println("Не удалось выполнить соединение с СУБД");
        }
        return conn;
    }
}
