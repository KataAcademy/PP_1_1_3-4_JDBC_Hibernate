package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String URLFIXED =
            "jdbc:mysql://localhost:3306/ppbd?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123";

    public static Connection getConnection() {
        Connection con;
        try {
            con = DriverManager.getConnection(URLFIXED, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return con;
    }
}

