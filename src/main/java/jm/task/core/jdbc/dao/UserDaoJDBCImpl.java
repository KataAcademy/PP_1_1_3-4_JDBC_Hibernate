package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getConnection;

public class UserDaoJDBCImpl  implements UserDao  {
    private Connection connection ;

    public UserDaoJDBCImpl() {

        this.connection = Util.getConnection();
    }

    public void createUsersTable() {
        String sqlCreate = "CREATE TABLE IF NOT EXISTS users (id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(50), lastName VARCHAR(50), age TINYINT)";

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sqlCreate);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void dropUsersTable() {
        String sqlDrop = "DROP TABLE IF EXISTS users";
        try
                (Statement statement = connection.createStatement()){
            statement.execute(sqlDrop);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)")) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String sqlRemoveUserById = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlRemoveUserById)){
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sqlGetAllUsers = "SELECT * FROM users";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlGetAllUsers)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }


    public void cleanUsersTable() {
        String sqlCleanUsersTable = "DELETE FROM users";
        try(Statement statement = connection.createStatement()){
            statement.executeUpdate(sqlCleanUsersTable);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
