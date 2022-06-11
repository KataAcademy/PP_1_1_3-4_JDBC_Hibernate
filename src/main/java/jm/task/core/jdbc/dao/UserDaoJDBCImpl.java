package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    private final Connection connection = Util.getConnection();

    public void createUsersTable() throws SQLException {
        var stm = connection.createStatement();
        stm.executeUpdate("CREATE TABLE IF NOT EXISTS users (id INT PRIMARY KEY AUTO_INCREMENT," +
                "name VARCHAR(25), lastName VARCHAR(25), age INT);");
    }

    public void dropUsersTable() throws SQLException {
        var stm = connection.createStatement();
        stm.executeUpdate("DROP TABLE IF EXISTS users");
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        var pstm = connection.prepareStatement("INSERT INTO users (name, lastName, age) values (?, ?, ?)");
        pstm.setString(1, name);
        pstm.setString(2, lastName);
        pstm.setByte(3, age);
        pstm.executeUpdate();
        System.out.println("User с именем - " + name + " добавлен в базу данных");
    }

    public void removeUserById(long id) throws SQLException {
        var pstm = connection.prepareStatement("DELETE FROM users WHERE id = ?");
        pstm.setLong(1, id);
        pstm.executeUpdate();
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> userList = new ArrayList<>();
        var pstm = connection.prepareStatement("SELECT * FROM users");
        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()) {
            User u = new User();
            u.setId(resultSet.getLong("id"));
            u.setName(resultSet.getString("name"));
            u.setLastName(resultSet.getString("lastName"));
            u.setAge(resultSet.getByte("age"));
            userList.add(u);
        }
        return userList;
    }

    public void cleanUsersTable() throws SQLException {
        var stm = connection.createStatement();
        stm.executeUpdate("DELETE FROM users");
    }
}
