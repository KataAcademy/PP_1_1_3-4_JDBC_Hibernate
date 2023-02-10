package jm.task.core.jdbc.util;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;
public class Util {
    // реализуйте настройку соеденения с БД
    public static final String USER_NAME = "root";
    public static final String PASSWORD = "root";
    public static final String URL = "jdbc:mysql://localhost:3306/mydbtest";
    public static Statement statement;

    public static Connection getConnection() {

        try {
            return DriverManager.getConnection(URL,USER_NAME,PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}