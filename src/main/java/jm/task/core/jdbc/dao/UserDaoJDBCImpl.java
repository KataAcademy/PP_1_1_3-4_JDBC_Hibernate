package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String SQL = "CREATE TABLE User\n" +
                "(\n" +
                "    Id BIGINT auto_increment,\n" +
                "    FirstName NVARCHAR(20),\n" +
                "    LastName NVARCHAR(20),\n" +
                "    Age TINYINT,\n" +
                ")";
        try {
            Statement statement = Util.getConnection();
            ResultSet resultSet = statement.executeQuery(SQL);
            }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public void dropUsersTable() {

    }

    public void saveUser(String name, String lastName, byte age) {
        String SQL



    }

    public void removeUserById(long id) {

    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String SQL = "SELECT * FROM User";
        try {
            Statement statement = Util.getConnection();
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()){
                User user = new User();
                user.setId((long) resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setName(resultSet.getString("lastName"));
                user.setAge((byte) resultSet.getInt("age"));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public void cleanUsersTable() {

    }
}
