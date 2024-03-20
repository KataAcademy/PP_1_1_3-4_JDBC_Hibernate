package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/Kata";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "05081968";

    // Нельзя создать обьект
    private Util() {
    }

    // public = Небезопасно, нужен DI, IOC.
    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Ошибка подключения к базе данных:");
            e.printStackTrace();
        }
        return connection;
    }
}
