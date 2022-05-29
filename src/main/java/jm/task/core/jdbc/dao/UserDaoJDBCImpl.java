package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.exception.DaoException;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private static final String CREATE_TABLE_SQL = """
            CREATE TABLE if NOT EXISTS USER (
                id BIGINT PRIMARY KEY AUTO_INCREMENT,
                NAME VARCHAR(32) NOT NULL,
                last_name VARCHAR(32) NOT NULL,
                age TINYINT NOT NULL
            );
            """;

    private static final String DROP_TABLE_SQL = """
            DROP TABLE if EXISTS USER;
            """;

    private static final String FIND_ALL_SQL = """
            SELECT user.id,
                   user.name,
                   user.last_name,
                   user.age
              FROM user
            """;

    private static final String SAVE_SQL = """
            INSERT
              INTO user (name, last_name, age)
            VALUES (?, ?, ?)
            """;

    private static final String DELETE_BY_ID_SQL = """
            DELETE
              FROM user
             WHERE user.id = ?
            """;

    private static final String CLEAR_TABLE_SQL = """
            TRUNCATE user
            """;

    private static final Connection connection = Util.open();

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try (var statement = connection.createStatement()) {
            statement.executeUpdate(CREATE_TABLE_SQL);
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    public void dropUsersTable() {
        try (var statement = connection.createStatement()) {
            statement.executeUpdate(DROP_TABLE_SQL);
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (var preparedStatement = connection.prepareStatement(SAVE_SQL)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.executeUpdate();
            System.out.println("User с именем - " + name + " " + lastName + " добавлен в базу данных");
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    public void removeUserById(long id) {
        try (var preparedStatement = connection.prepareStatement(DELETE_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    public List<User> getAllUsers() {
        try (var preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            var resultSet = preparedStatement.executeQuery();
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                users.add(new User(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("last_name"),
                        resultSet.getByte("age")));
            }
            return users;
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    public void cleanUsersTable() {
        try (var preparedStatement = connection.prepareStatement(CLEAR_TABLE_SQL)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}