package jm.task.core.jdbc.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

public Connection getConnectionToDB() throws SQLException {
        // реализуйте настройку соеденения с БД
        String url = "jdbc:mysql://localhost:3306/test";
        String userName = "ususer";
        String pwd = "UD@~VX%uDOpBF7";

        return DriverManager.getConnection(url, userName, pwd);
    }
}
