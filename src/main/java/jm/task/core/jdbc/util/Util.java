package jm.task.core.jdbc.util;


import java.sql.*;

public class Util {
    private static Connection con = null;

    private static final String URL = "jdbc:mysql://localhost:3306/try1";
    private static final String USER = "root";
    private static final String PASS = "0xe21b706dd8fd1bc2da2821d7247a1cd36a00e290";

    public static Connection getConnection() {
        try {
            con = DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
}