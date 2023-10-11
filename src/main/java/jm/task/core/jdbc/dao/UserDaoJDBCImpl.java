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
        try (Connection connection = getConnection()) {
            String sqlCommand = "CREATE TABLE users (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(40), lastName VARCHAR(40), age INT);";
            try (PreparedStatement statement = connection.prepareStatement(sqlCommand)) {
                statement.executeUpdate();
            } catch (SQLException ignored) {
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public void dropUsersTable() {
        try (Connection connection = getConnection()) {
            String sqlCommand = "DROP TABLE users;";
            try (PreparedStatement statement = connection.prepareStatement(sqlCommand)) {
                statement.executeUpdate();
            } catch (SQLException ignored) {
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        try (Connection connection = getConnection()) {
            String sqlCommand = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?);";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand,
                    Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, lastName);
                preparedStatement.setLong(3, age);

                preparedStatement.executeUpdate();

                System.out.println("User с именем " + name + " добавлен в базу данных");
            }
        }
    }

    public void removeUserById(long id) throws SQLException {
        try (Connection connection = getConnection()) {
            String sqlCommand = "DELETE FROM users WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand)) {
                preparedStatement.setLong(1, id);
                preparedStatement.executeUpdate();
            }
        }
    }

    public List<User> getAllUsers() {
        try (Connection connection = getConnection()) {
            List<User> userList = new ArrayList<>();
            String sqlCommand = "SELECT id, name, lastName, age FROM users;";
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(sqlCommand);
                while (resultSet.next()) {
                    User user = new User();
                    user.setId(resultSet.getLong("id"));
                    user.setName(resultSet.getString("name"));
                    user.setLastName(resultSet.getString("lastName"));
                    user.setAge(resultSet.getByte("age"));

                    userList.add(user);
                }
            } catch (SQLException e) {
                throw new RuntimeException();
            }
            return userList;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public void cleanUsersTable() throws SQLException {
        try (Connection connection = getConnection()) {
            String sqlCommand = "DELETE FROM users;";
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate(sqlCommand);
            }
        }
    }
}
