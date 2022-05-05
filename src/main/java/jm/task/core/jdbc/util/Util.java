package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String URL = "jdbc:mariadb://localhost:3306/test_database";
    private static final String USER = "test_user";
    private static final String PASSWORD = "Password";

    public static Connection getDbConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
