package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public  class Util {
    // реализуйте настройку соеденения с БД
    private static final String userName ="root";
    private static final String password = "1111";
    private static final String connectURL = "jdbc:mysql://localhost:3306/task";
    private static Connection connection;




    public static Connection getConnection()  {
        if (connection == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(connectURL, userName, password);
                System.out.println("getConnection");
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println("Do not open - Exception!!!");
            }
        }
        return connection;
    }
    public static void closeConnection(){
        if(connection != null) {
            try {
                System.out.println("closeConnection");
                connection.close();
            } catch (SQLException e) {
                System.out.println("Do not closed - Exception!!!");
            }
        }
    }

}
