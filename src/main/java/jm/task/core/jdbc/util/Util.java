package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/my_db2";
    private static final String DB_USERNAME = "bestuser1";
    private static final String DB_PASSWORD = "bestuser1";


    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName(DB_DRIVER);
            con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            System.out.println("Connection OK");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Connection Error");
        }
        return con;
    }
//    public static void main(String[] args) {
//        Connection connection;
//        try {
//            Driver driver = new com.mysql.jdbc.Driver()
//            DriverManager.registerDriver(driver);
//
//            connection = DriverManager.getConnection(DB_URL,DB_USERNAME, DB_PASSWORD);
//            if(!connection.isClosed()) {
//                System.out.println("Database connection established");
//            }
//            connection.close();
//            if(connection.isClosed()) {
//                System.out.println("Database connection closed");
//            }
//
//        } catch (SQLException e) {
//            System.err.println("Error");
//        }
//    }
}
