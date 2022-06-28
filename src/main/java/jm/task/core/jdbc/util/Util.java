package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static Util util;
    private Connection connection;

    private Connection UtilConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String userName = "root";
            String password = "Onesteptodie2603+";
            String dbName = "kata";
            String hostName = "localhost";
            String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;
            this.connection = DriverManager.getConnection(connectionURL, userName, password);
        } catch (ClassNotFoundException ex) {
            System.out.println("Database Connection Creation Failed : " + ex.getMessage());
        }
        return connection;
    }

    public Connection getConnection() throws SQLException {
        return UtilConnection();
    }

    public static Util getInstance() throws SQLException {
        if (util == null) {
            util = new Util();
        } else if (util.getConnection().isClosed()) {
            util = new Util();
        }
        return util;
    }
}