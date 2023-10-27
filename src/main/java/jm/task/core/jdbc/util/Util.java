package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД

    private static String URL = "jdbc:mysql://localhost:3306/mydb";
    private static String LOGIN = "root";
    private static String PASSWORD = "Panfil23";

    public Util() {
        try {
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
        } catch (SQLException ignored) {
        }

    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, LOGIN, PASSWORD);
        }
        catch (SQLException ignored) {

        }
        return null;
    }

}
