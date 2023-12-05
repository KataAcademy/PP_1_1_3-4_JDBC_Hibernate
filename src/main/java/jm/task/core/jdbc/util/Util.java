package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {
    // реализуйте настройку соеденения с БД
    public static Statement getConnection() {
        try {
            String url =  "jdbc:mysql://localhost/users";
            String login =  "root";
            String pass =  "12345678";

            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();

            Connection connection = DriverManager.getConnection(url ,login, pass);
            return connection.createStatement();


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
}
