package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    public static Connection getMySQLConnection() {

        try {
            String hostName = "localhost";
            String dbName = "kata";
            String userName = "root";
            String password = "Onesteptodie2603+";
            Class.forName("com.mysql.cj.jdbc.Driver");
            String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;
            return DriverManager.getConnection(connectionURL, userName,
                    password);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}