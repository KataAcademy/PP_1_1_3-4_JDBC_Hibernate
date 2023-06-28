package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {

    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            connect();
        }
        return connection;
    }

    private static void connect() {

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "password");

            if (!connection.isClosed()) {
                System.out.println("Соединение установлено");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Невозможно подключиться к БД");
        }

    }

}
