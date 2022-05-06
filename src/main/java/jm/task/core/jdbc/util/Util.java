package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Util {
    private static final Logger logger = Logger.getLogger(Util.class.getCanonicalName());
    private static final String URL = "jdbc:mysql://localhost:3307/db";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    public static Connection getDbConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Не удалось установить соединение с БД: " + e);
            return null;
        }
    }
}
