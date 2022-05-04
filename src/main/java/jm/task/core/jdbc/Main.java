package jm.task.core.jdbc;

import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        System.out.println("Get connection ... ");

        // Get a Connection object
        Connection conn = null;
        try {
            conn = Util.getDbConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Get connection " + conn);

        System.out.println("Done!");
        // реализуйте алгоритм здесь
    }
}
