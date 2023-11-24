package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Класс для подключения к базе данных.
 */
public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/jdbc";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "192168";
    private static Util instance;
    private Connection connection;

    /**
     * Приватный конструктор класса Util.
     * Инициализирует подключение к базе данных.
     * Если происходит ошибка SQLException, то она будет распечатана.
     */
    private Util() {
        try {
            this.connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Возвращает экземпляр класса Util.
     * Если экземпляр не создан, то создает новый экземпляр и возвращает его.
     *
     * @return экземпляр класса Util
     */
    public static Util getInstance() {
        if (instance == null) {
            instance = new Util();
        }
        return instance;
    }

    /**
     * Возвращает объект типа Connection, представляющий подключение к базе данных.
     * Если подключение уже открыто, то возвращает его.
     * Если подключение закрыто или не создано, то создает новое подключение и возвращает его.
     *
     * @return объект типа Connection
     * @throws SQLException если происходит ошибка при подключении к базе данных
     */
    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }
        return connection;
    }
}