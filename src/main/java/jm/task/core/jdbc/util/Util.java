package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static Util util;
    private final Connection connection;

    private Util() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String userName = "root";
            String password = "Onesteptodie2603+";
            String dbName = "kata";
            String hostName = "localhost";
            String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;
            this.connection = DriverManager.getConnection(connectionURL, userName, password);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
        return connection;
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