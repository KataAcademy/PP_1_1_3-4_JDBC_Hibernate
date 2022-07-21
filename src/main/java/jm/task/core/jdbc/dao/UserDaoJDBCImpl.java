package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private String createUsersTable = "CREATE TABLE IF NOT EXISTS User \n" +
            "(\n" +
            "\tId BIGINT auto_increment primary key, \n" +
            "\tFirstName VARCHAR(20), \n" +
            "    LastName VARCHAR(20),\n" +
            "\tAge TINYINT\n" +
            ");";
    private String dropUserTable = "drop table if exists User;";
    private String saveUser = "INSERT INTO user (FirstName, LastName, age) VALUES (?,?,?)";
    private String removeUserById = "DELETE FROM User WHERE Id = ";
    private String getAllUsers = "SELECT * FROM User";
    private String cleamUsersTable = "TRUNCATE TABLE User";

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection connection = Util.getConnectionJDBC(); Statement statement = connection.createStatement()){
            statement.executeUpdate(createUsersTable);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try(Connection connection = Util.getConnectionJDBC(); Statement statement = connection.createStatement()) {
            statement.execute(dropUserTable);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try(Connection connection =Util.getConnectionJDBC(); PreparedStatement preparedStatement = connection.prepareStatement(saveUser)){
            preparedStatement.setString(1,name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User с именем - " + name + " добавлен в базу данных ");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = Util.getConnectionJDBC(); Statement statement = connection.createStatement()) {
            statement.execute(removeUserById + id);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Connection connection = Util.getConnectionJDBC(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(getAllUsers);
            while (resultSet.next()){
                User newUser = new User();
                newUser.setId(resultSet.getLong("id"));
                newUser.setName(resultSet.getString("FirstName"));
                newUser.setLastName(resultSet.getString("lastName"));
                newUser.setAge(resultSet.getByte("age"));
                userList.add(newUser);
                System.out.println(newUser);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return userList;
    }

    public void cleanUsersTable() {
        try (Connection connection = Util.getConnectionJDBC(); Statement statement = connection.createStatement()){
            statement.executeUpdate(cleamUsersTable);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
