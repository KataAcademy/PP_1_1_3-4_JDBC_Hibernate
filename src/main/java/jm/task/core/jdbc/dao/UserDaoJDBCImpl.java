package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private final Statement statement = Util.getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        try {

            String sqlCommand = "CREATE TABLE IF NOT EXISTS Users (id INT PRIMARY KEY auto_increment, name VARCHAR(255), lastName VARCHAR(255), age INT)";
            statement.executeUpdate(sqlCommand);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void dropUsersTable() {

        try  {
            String sqlCommand = "DROP TABLE IF EXISTS Users";
            statement.executeUpdate(sqlCommand);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try  {
            String sqlCommand = String.format("INSERT INTO Users(name, lastName, age) VALUE('%s', '%s', %d)", name, lastName, age);
            statement.executeUpdate(sqlCommand);
            System.out.println(name + " добавлен в базу данных");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void removeUserById(long id) {
        try  {
            String sqlCommand = "DELETE FROM Users WHERE id=" + id;
            statement.executeUpdate(sqlCommand);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<User> getAllUsers() {
        try {
            List<User> users = new ArrayList<>();
            String sqlCommand = "SELECT * FROM Users";
            ResultSet resultSet = statement.executeQuery(sqlCommand);

            while (resultSet.next()) {
                users.add(new User(resultSet.getString("name"), resultSet.getString("lastName"), resultSet.getByte("age")));
            }
            return users;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public void cleanUsersTable() {
        try {
            String sqlCommand = "DELETE FROM Users";
            statement.executeUpdate(sqlCommand);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
