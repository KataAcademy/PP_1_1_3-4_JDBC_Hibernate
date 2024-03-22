package jm.task.core.jdbc.util;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Util {
    private static final Logger logger = Logger.getLogger(Util.class.getName());
    private static final String URL = "jdbc:mysql://localhost:3306/Kata";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    // Нельзя создать обьект
    private Util() {
    }

    // public = Небезопасно, нужен DI, IOC.
    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Ошибка подключения к базе данных:", e);
        }
        return connection;
    }
}
