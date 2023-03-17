package jm.task.core.jdbc.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String url = "jdbc:mysql://localhost:3306/test";
    private static final String userName = "ususer";
    private static final String pwd = "UD@~VX%uDOpBF7";

    public static Connection getConnectionToDB() {
        // реализуйте настройку соеденения с БД
        try {
            return DriverManager.getConnection(url, userName, pwd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
