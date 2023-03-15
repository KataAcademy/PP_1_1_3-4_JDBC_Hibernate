package jm.task.core.jdbc.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private final String url = "jdbc:mysql://localhost:3306";
    private final String userName = "ususer";
    private final String pwd = "UD@~VX%uDOpBF7";

    public Connection getConnectionToDB() throws SQLException {
        return DriverManager.getConnection(url, userName, pwd);
    }
}
