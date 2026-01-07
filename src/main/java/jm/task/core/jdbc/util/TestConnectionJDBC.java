package jm.task.core.jdbc.util;

import java.sql.Connection;

public class TestConnectionJDBC {
    public static void main(String[] args) {
        System.out.println("=== Тестируем подключение к БД ===");

        Connection conn = Util.getConnection();

        if (conn != null) {
            System.out.println("✅ УСПЕХ! Подключение работает.");
            try {
                conn.close();
                System.out.println("Соединение закрыто.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("❌ ПРОВАЛ. Нет подключения.");
        }
    }
}