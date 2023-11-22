package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {

    private Connection connection = getConnect();

    public void createUsersTable() {

        try(Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS users(" +
                    "id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(255) NOT NULL," +
                    "lastname VARCHAR(255) NOT NULL," +
                    "age TINYINT UNSIGNED)");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {

        try(Statement statement = connection.createStatement()) {
            statement.execute("DROP TABLE IF EXISTS users");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        String sql = "INSERT INTO users(name, lastname, age) VALUES (?, ?, ?)";
        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setByte(3, age);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {

        String sql = "DELETE FROM users WHERE id = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() {
        List<User> usersList = new ArrayList<>();

        try(Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAge(resultSet.getByte("age"));

                usersList.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usersList;
    }

    public void cleanUsersTable() {

        try(Statement statement = connection.createStatement()) {
            statement.execute("TRUNCATE TABLE users");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
