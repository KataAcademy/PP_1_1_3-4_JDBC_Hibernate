package jm.task.core.jdbc.util;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.*;


public class Util {
    // реализуйте настройку соеденения с БД
    private static final String URL = "jdbc:mysql://localhost:3306/mydb_09.2022?serverTimezone=Europe/Moscow&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Sql2022.";
    private static final String CONNECTION_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static Connection connection = null;

    private Util() {
    }

    public static Connection getConnection() {
        try {
            Class.forName(CONNECTION_DRIVER).getDeclaredConstructor().newInstance();
            System.out.println("Попытка соединения к БАЗЕ ДАННЫХ..........");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            connection.setAutoCommit(false);
            System.out.println("Соединение установленно!\n");
        } catch (SQLException | ClassNotFoundException | NoSuchMethodException | InstantiationException |
                 IllegalAccessException | InvocationTargetException e) {
            System.err.print("Что-то пошло не так!\nСООБЩЕНИЕ ОБ ОШИБКЕ: " + e);
        }
        return connection;
    }
}
