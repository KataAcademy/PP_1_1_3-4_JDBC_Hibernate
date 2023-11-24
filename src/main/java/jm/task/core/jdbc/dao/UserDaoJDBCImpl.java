package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс UserDaoJDBCImpl реализует интерфейс UserDao и предоставляет
 * методы для взаимодействия с таблицей Users в базе данных через JDBC.
 *
 * Каждый метод этого класса отвечает за выполнение определенного SQL-запроса.
 * Это включает в себя операции создания таблицы, удаления таблицы,
 * добавления, удаления по id, извлечения пользователей и очистки таблицы.
 *
 * Все методы обрабатывают SQLExceptions, возникшие в процессе выполнения SQL-запросов.
 *
 * Этот класс зависит от класса Util для получения соединения с базой данных.
 */
public class UserDaoJDBCImpl implements UserDao {

    /**
     * Создаёт таблицу Users в базе данных, если она ещё не существует.
     * Таблица содержит следующие поля:
     * - id: уникальный идентификатор пользователя (INT PRIMARY KEY AUTO_INCREMENT)
     * - name: имя пользователя (VARCHAR(50))
     * - lastName: фамилия пользователя (VARCHAR(50))
     * - age: возраст пользователя (TINYINT)
     *
     * В случае возникновения ошибок SQL они будут отловлены и распечатаны.
     */
    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS Users (" +
                "id INT PRIMARY KEY AUTO_INCREMENT, " +
                "name VARCHAR(50), " +
                "lastName VARCHAR(50), " +
                "age TINYINT)";

        try (Connection connection = Util.getInstance().getConnection();
             Statement stmt = connection.createStatement()) {

            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Удаляет таблицу Users из базы данных, если она существует.
     *
     * В случае возникновения ошибок SQL они будут отловлены и распечатаны.
     */
    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS Users";

        try (Connection connection = Util.getInstance().getConnection();
             Statement stmt = connection.createStatement()) {

            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Сохраняет данные пользователя в таблице Users.
     *
     * @param name Имя пользователя. Не может быть null и должно содержать не более 50 символов.
     * @param lastName Фамилия пользователя. Не может быть null и должно содержать не более 50 символов.
     * @param age Возраст пользователя. Должен быть положительным числом.
     *
     * В случае ошибок SQL они будут отловлены и распечатаны.
     */
    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO Users (name, lastName, age) VALUES (?, ?, ?)";

        try (Connection connection = Util.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.setString(2, lastName);
            pstmt.setByte(3, age);

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("User с именем - " + name + " добавлен в базу данных.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Удаляет пользователя из таблицы Users по заданному ID.
     *
     * @param id Уникальный идентификатор пользователя для удаления. Должен быть положительным числом.
     *
     * В случае ошибок SQL они будут отловлены и распечатаны.
     */
    public void removeUserById(long id) {
        String sql = "DELETE FROM Users WHERE id = ?";

        try (Connection connection = Util.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Возвращает список всех пользователей из таблицы Users.
     *
     * @return Список пользователей. Если в таблице нет пользователей, возвращает пустой список.
     *
     * В случае ошибок SQL они будут отловлены и распечатаны.
     */
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        String sql = "SELECT * FROM Users";

        try (Connection connection = Util.getInstance().getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                long id = rs.getLong("id");
                String name = rs.getString("name");
                String lastName = rs.getString("lastName");
                byte age = rs.getByte("age");

                User user = new User(name, lastName, age);
                user.setId(id);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    /**
     * Удаляет все записи из таблицы Users, оставляя таблицу пустой.
     *
     * В случае ошибок SQL они будут отловлены и распечатаны.
     */
    public void cleanUsersTable() {
        String sql = "DELETE FROM Users";

        try (Connection connection = Util.getInstance().getConnection();
             Statement stmt = connection.createStatement()) {

            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
