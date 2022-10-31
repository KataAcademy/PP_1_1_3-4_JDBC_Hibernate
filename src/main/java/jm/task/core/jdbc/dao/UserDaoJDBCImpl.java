package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static Statement statement;
    private static Connection connection;


    static {
        try {
            connection = Util.getConnection();
            connection.setAutoCommit(false);
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String sql = "CREATE TABLE if NOT EXISTS Users (id bigint AUTO_INCREMENT, name VARCHAR(15) NOT NULL, lastName VARCHAR(15) NOT NULL, age TINYINT NOT NULL, primary key(id))";
        try {
            statement.executeUpdate(sql);
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS Users";
        try {
            statement.executeUpdate(sql);
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO Users(name, lastName, age) VALUES (?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            connection.commit();
            System.out.printf("User с именем - %s добавлен в базу данных%n",name);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM users WHERE id=?";
        String sql2 = "SELECT * FROM Users where id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql2);
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (!rs.next()) {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setLong(1, id);
                preparedStatement.executeUpdate();
                connection.commit();
            }
        } catch (SQLException e) {
            throw  new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        String sql = "SELECT * FROM Users";
        List<User> users = new ArrayList<>();

        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println("Error");
        }
        return users;
    }

    public void cleanUsersTable() {
        String sql = "truncate table Users";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            System.out.println("Error");
        }
    }
}
