package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    @Override
    public void createUsersTable() {
        try {
            PreparedStatement preparedStatement = Util.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS Users (id INTEGER PRIMARY KEY AUTO_INCREMENT, name TEXT,lastName TEXT,age DOUBLE);");
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void dropUsersTable() {
        try {
            PreparedStatement preparedStatement = Util.getConnection().prepareStatement("DROP TABLE IF EXISTS USERS;");
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            PreparedStatement preparedStatement = Util.getConnection().prepareStatement("INSERT INTO USERS (name, lastName, age) VALUES (?, ?, ?);");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void removeUserById(long id) {

        try {
            PreparedStatement preparedStatement = Util.getConnection().prepareStatement("DELETE FROM USERS WHERE ID = ?;");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = Util.getConnection().prepareStatement("SELECT * FROM USERS;");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                list.add(
                        new User(
                                resultSet.getString("name"),
                                resultSet.getString("lastName"),
                                resultSet.getByte("age"))
                );

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    @Override
    public void cleanUsersTable() {

        try {
            PreparedStatement preparedStatement = Util.getConnection().prepareStatement("DELETE FROM USERS;");
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
