package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        // Statement - интерфейс для доступа к БД
        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) { // создаю экземпляр statement для выполнения запросов
            // использую executeUpdate т.к. знаю что строка содержит запрос
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users (" + // создаю таблицу если ее еще нет
                    "id BIGINT PRIMARY KEY AUTO_INCREMENT, " +
                    "name VARCHAR(255), " +
                    "lastName VARCHAR(255), " +
                    "age INT)");
            connection.setAutoCommit(false); // устанавливаю статус фиксации в false и завершу методом commit
            //connection.commit(); // комиты можно не использовать
            //можно без роллбэка - автозакрытие сделает роллбэк
            System.out.println("Таблица создана");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Таблица НЕ создана!");
        }
    }

    public void dropUsersTable() {
        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS users"); // удаляю таблицу если она существует
            connection.setAutoCommit(false);
            System.out.println("Таблица удалена");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Таблица НЕ удалена!");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        // при вставке использую PreparedStatement, потому что параметры динамические принимает
        try (Connection connection = Util.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO users (name, lastName, age) VALUES (?, ?, ?);")) { // значения я пока не знаю
            statement.setString(1, name); // устанавливаю значения вместо ??? (индексы начинаются с 1, а не с 0
            statement.setString(2, lastName);
            statement.setInt(3, age);
            statement.executeUpdate(); // отправляю запрос
            connection.setAutoCommit(false);
            System.out.println("User " + name + " " + lastName + " добавлен"); // для индикации
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("User " + name + " " + lastName + " не может быть добален в таблицу!");
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = Util.getConnection(); PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE Id = ?")) {
            statement.setLong(1, id); // удаляю первого студента
            statement.executeUpdate(); // отправляю запрос
            connection.setAutoCommit(false);
            System.out.println("User с ID = " + id + " удален");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("User с ID = " + id + " не может быть удален!");
        }
    }

    public List<User> getAllUsers() {
        List<User> listUser = new ArrayList<>();
        Connection connection = Util.getConnection();
        try {
            String sql = "SELECT * FROM users";
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery(sql); //возвращаю данные которые запросила в ResultSet(который хранит данные из запроса)
            connection.setAutoCommit(true);
            while (res.next()) { // до тех пор пока в хранилище есть данные
                User user = new User( // создаю юзера
                        res.getString("name"), // получаю данные из таблицы
                        res.getString("lastName"),
                        (byte) res.getInt("age")
                );
                user.setId(res.getLong("id")); // устанавливаю id-шники, полученные из хранилища
                listUser.add(user); // добавляю юзера в лист
                System.out.println("Данные из таблицы получены");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ошибка чтения из таблицы!");
        }
        return listUser; // возвращаю лист с юзерами
    }

    public void cleanUsersTable() {
        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            String sql = "DELETE FROM users"; // создаю запроc
            statement.executeUpdate(sql); // отправляю запрос на удаление
            connection.setAutoCommit(false);
            System.out.println("Таблица очищена");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Таблица не может быть очищена!");
        }
    }
}
