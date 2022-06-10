package jm.task.core.jdbc.util;


import java.sql.*;

public class Util {
    private static Connection con = null;

    static {
        String url = "jdbc:mysql://localhost:3306/try1";
        String user = "root";
        String pass = "0xe21b706dd8fd1bc2da2821d7247a1cd36a00e290";
        try {
            con = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return con;
    }
}