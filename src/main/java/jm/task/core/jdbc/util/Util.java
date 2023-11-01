package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД

    private Connection connection;
    private static String URL = "jdbc:mysql://localhost:3306/mydb";
    private static String LOGIN = "root";
    private static String PASSWORD = "root";

    public Util() {


    }

    public Connection getConnection() {
        try {
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
        } catch (SQLException ignored) {
        }
        return connection;
    }

    public static void main(String[] args) throws SQLException {
        Util util = new Util();
        Connection connection1 = util.getConnection();
        if (!connection1.isClosed()) {
            System.out.println("Соеденение установлено");
        }
    }

}
