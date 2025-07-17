package jm.task.core.jdbc.dao;

import com.sun.xml.bind.v2.model.core.ID;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {
//    private final Connection connection = getConnection();

    public UserDaoJDBCImpl() {

    }

    @Override
    public void createUsersTable() {

        String sql = "CREATE TABLE IF NOT EXISTS users (" +
                "id INT PRIMARY KEY AUTO_INCREMENT, " +
                "name VARCHAR(45), " +
                "lastName VARCHAR(45), " +
                "age TINYINT)";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(sql);
            System.out.println("Database created");

        } catch (SQLException e) {
            throw new RuntimeException("Failed to create database", e);
        }
    }

    @Override
    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS users";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(sql);
            System.out.println("Database deleted");

        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete database", e);
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        String sql = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Failed to save user", e);
        }
    }

    @Override
    public void removeUserById(long id) {
        String sql = "DELETE FROM users WHERE id = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            System.out.println("User deleted");

        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete user", e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> usersList = new ArrayList<>();

        String sql = "SELECT id, name, lastName, age FROM users";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                usersList.add(user);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve users", e);
        }

        return usersList;
    }

    @Override
    public void cleanUsersTable() {
        String sql = "TRUNCATE TABLE users";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.executeUpdate();
            System.out.println("Table cleared");

        } catch (SQLException e) {
            throw new RuntimeException("Failed to clean table", e);
        }
    }
}
