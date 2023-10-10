package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String URLFIXED =
            "jdbc:mysql://localhost:3306/ppbd?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true" +
                    "&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123";

    public static Connection getConnection() throws SQLException {
        Connection con;
        try {
            con = DriverManager.getConnection(URLFIXED, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
        return con;
    }
}
