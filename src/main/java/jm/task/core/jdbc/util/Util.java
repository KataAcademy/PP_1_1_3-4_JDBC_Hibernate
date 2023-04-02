package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    static String dbUrl = "jdbc:mysql://localhost/kata31_03_2023?serverTimezone=Europe/Moscow&useSSL=false";
    static String username = "root";
    static String password = "MysqlP@ss2023";

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(dbUrl, username, password);
        System.out.println("We're connected!!");
        return connection;
    }
}
