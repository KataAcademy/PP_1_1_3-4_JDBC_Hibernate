package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    @Override
    public void createUsersTable() {
        try (PreparedStatement prepSt = Util.getConnectionToDB()
                .prepareStatement("CREATE TABLE IF NOT EXISTS users" +
                        "(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                        " name VARCHAR(255)," +
                        " lastName VARCHAR(255)," +
                        " age TINYINT)")
        ) {
            prepSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try (PreparedStatement prepSt = Util.getConnectionToDB()
                .prepareStatement("DROP TABLE IF EXISTS users")
        ) {
            prepSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement prepSt = Util.getConnectionToDB()
                .prepareStatement("INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)")
        ) {
            prepSt.setString(1, name);
            prepSt.setString(2, lastName);
            prepSt.setByte(3, age);
            prepSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (PreparedStatement prepSt = Util.getConnectionToDB()
                .prepareStatement("DELETE FROM users WHERE id = ?")
        ) {
            prepSt.setLong(1, id);
            prepSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (PreparedStatement prepSt = Util.getConnectionToDB()
                .prepareStatement("SELECT * FROM users");
             ResultSet resultSet = prepSt.executeQuery()
        ) {
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastName");
                byte age = resultSet.getByte("age");
                User someUser = new User(name, lastName, age);
                someUser.setId(id);
                userList.add(someUser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        try (PreparedStatement prepSt = Util.getConnectionToDB()
                .prepareStatement("TRUNCATE TABLE users")
        ) {
            prepSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
