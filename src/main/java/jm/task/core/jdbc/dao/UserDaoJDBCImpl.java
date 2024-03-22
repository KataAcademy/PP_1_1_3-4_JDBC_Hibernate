package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection connection = Util.getConnection();
    private static final Logger logger = Logger.getLogger(UserDaoJDBCImpl.class.getName());


    public void createUsersTable() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS User ("
                + "id INT AUTO_INCREMENT PRIMARY KEY,"
                + "name VARCHAR(255),"
                + "lastName VARCHAR(255),"
                + "age TINYINT"
                + ")";

        try (Statement statement = connection.createStatement()) {
            statement.execute(createTableSQL);
            logger.info("Таблица пользователей создана успешно");
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Неудалось создать таблицу \n", e);
        }
    }

    public void dropUsersTable() {
        String createTableSQL = "DROP TABLE User";

        try (Statement statement = connection.createStatement()) {
            statement.execute(createTableSQL);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Неудалось удалить таблицу \n", e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String insertSQL = "INSERT INTO User (name, lastName, age) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            String message = String.format("User с именем — %s не добавлен в базу данных", name);
            logger.log(Level.SEVERE, message, e);
        }
    }

    public void removeUserById(long id) {
        String deleteSQL = "DELETE FROM User WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            String message = String.format("Неудаось удалить User'a с id - %d", id);
            logger.log(Level.SEVERE, message, e);
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String selectSQL = "SELECT * FROM User";

        try(Statement statement = connection.createStatement()) {
            ResultSet resultSet =  statement.executeQuery(selectSQL);


            while (resultSet.next()){
                User user = new User();

                user.setId(resultSet.getLong("id"));
                user.setAge(resultSet.getByte("age"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));

                users.add(user);
            }
        } catch (SQLException e) {
            logger.info("Неудалось получить всех User'ов \n");
        }
        return users;
    }

    public void cleanUsersTable() {
        String truncateSQL = "TRUNCATE TABLE User";

        try (Statement statement = connection.createStatement()) {
            statement.execute(truncateSQL);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Ошибка при очистке таблицы", e);
        }
    }
}
