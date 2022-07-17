package jm.task.core.jdbc.util;

import java.sql.*;


public class Util {
    // реализуйте настройку соеденения с БД
    private static final String URL = "jdbc:mysql://localhost:3306/test";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    public static Statement getConnection() {
        Statement statement = null;
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            System.out.println("Connection succesfull");
            statement = connection.createStatement();

        } catch (SQLException e) {
            System.out.println("Connection error");
        }
        return statement;

    }
}
