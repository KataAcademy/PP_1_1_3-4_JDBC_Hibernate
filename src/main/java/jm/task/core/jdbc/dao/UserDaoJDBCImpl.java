package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.io.Closeable;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection connection = Util.getDbConnection();
    private static final Logger logger = Logger.getLogger(Util.class.getCanonicalName());


    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String query = "CREATE TABLE IF NOT EXISTS users (id BIGINT PRIMARY KEY AUTO_INCREMENT, first_name TEXT, last_name TEXT, age INT)";
        try {
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }

    public void dropUsersTable() {
        String query = "DROP TABLE IF EXISTS users";
        try {
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String query = "INSERT INTO users (first_name, last_name, age) VALUES (?, ?, ?)";

        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setByte(3, age);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }

    public void removeUserById(long id) {
        String query = "DELETE FROM users WHERE id = ?";

        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(query);
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        String query = "SELECT * FROM users";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setAge(rs.getByte("age"));
                list.add(user);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
        return list;
    }

    public void cleanUsersTable() {
        String query = "TRUNCATE TABLE users";
        try {
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }
}