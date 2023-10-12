package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getConnection;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String sqlCommand = "CREATE TABLE users (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(40), lastName VARCHAR(40), age INT);";
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(sqlCommand)) {
            statement.executeUpdate();
        } catch (SQLSyntaxErrorException ignored) {

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        String sqlCommand = "DROP TABLE users;";
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(sqlCommand)) {
            statement.executeUpdate();
        } catch (SQLSyntaxErrorException ignored) {

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sqlCommand = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?);";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand,
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setLong(3, age);

            preparedStatement.executeUpdate();

            System.out.println("User с именем " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        String sqlCommand = "DELETE FROM users WHERE id = ?";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String sqlCommand = "SELECT id, name, lastName, age FROM users;";
        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sqlCommand);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));

                userList.add(user);
            }
            return userList;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public void cleanUsersTable() {
        String sqlCommand = "DELETE FROM users;";
        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            statement.executeUpdate(sqlCommand);
        } catch (SQLSyntaxErrorException ignored) {

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

