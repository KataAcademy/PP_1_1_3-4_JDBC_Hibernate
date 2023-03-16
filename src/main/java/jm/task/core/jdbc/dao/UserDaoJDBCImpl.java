package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
Util databaseConnection = new Util();

public void createUsersTable() {
    try (Connection DBConnect = databaseConnection.getConnectionToDB()) {
        try (Statement statement = DBConnect.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS users" +
                    "(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                    " name VARCHAR(255)," +
                    " lastName VARCHAR(255)," +
                    " age TINYINT)");
        }
    } catch (SQLException e) {
        System.out.println("Couldn't connect to database");
        e.printStackTrace();
    }
}

public void dropUsersTable() {
    try (Connection DBConnect = databaseConnection.getConnectionToDB()) {
        try (Statement statement = DBConnect.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS users");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

public void saveUser(String name, String lastName, byte age) {
    try (Connection DBConnect = databaseConnection.getConnectionToDB()) {
        try (PreparedStatement preparedStatement = DBConnect.prepareStatement("INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)")) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

public void removeUserById(long id) {
    try (Connection DBConnect = databaseConnection.getConnectionToDB()) {
        try (PreparedStatement preparedStatement = DBConnect.prepareStatement("DELETE FROM users WHERE id = ?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

public List<User> getAllUsers() {
    List<User> userList = new ArrayList<>();
    try (Connection DBConnect = databaseConnection.getConnectionToDB()) {
        try (Statement statement = DBConnect.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery("SELECT * FROM users")) {
                while (resultSet.next()){
                    long id = resultSet.getLong("id");
                    String name = resultSet.getString("name");
                    String lastName = resultSet.getString("lastName");
                    byte age = resultSet.getByte("age");
                    User someUser = new User(name, lastName, age);
                    someUser.setId(id);
                    userList.add(someUser);
                }
            }

        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return userList;
}

public void cleanUsersTable() {
    try (Connection DBConnect = databaseConnection.getConnectionToDB()) {
        try (Statement statement = DBConnect.createStatement()) {
            statement.executeUpdate("TRUNCATE TABLE users");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
}
