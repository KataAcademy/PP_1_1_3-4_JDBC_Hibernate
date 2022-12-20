package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String dbUser = "test";
    private static final String dbPassword = "test";
    private static final String dbName = "test";
    private static final String dbHost = "localhost";
    private static final String dbPort = "3306";

    private static Connection connection = null;

    public static Connection getConnection() {
        try {
            String dbURL = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
            connection = DriverManager.getConnection(dbURL, dbUser, dbPassword);
            System.out.println("Connection OK");
        } catch (SQLException e) {
            System.err.println("Connection ERROR");
        }
        return connection;
    }

}
