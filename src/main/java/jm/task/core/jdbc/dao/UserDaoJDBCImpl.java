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
        try {
            Statement stmt = Util.getConnection().createStatement();
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Users (id INTEGER PRIMARY KEY AUTO_INCREMENT, name TEXT,lastName TEXT,age DOUBLE);");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try {
            Statement stmt = Util.getConnection().createStatement();
            stmt.executeUpdate("DROP TABLE IF EXISTS USERS;");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            Statement stmt = Util.getConnection().createStatement();
            stmt.executeUpdate(String.format(("INSERT INTO USERS (name, lastName, age) VALUES ('%s', '%s', %d)"), name, lastName, age));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void removeUserById(long id) {

        try {
            Statement stmt = Util.getConnection().createStatement();
            stmt.executeUpdate(String.format(("DELETE FROM USERS WHERE ID =(%d)"), id));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();

        try {
            Statement stmt = Util.getConnection().createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM USERS");

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

    public void cleanUsersTable() {

        try {
            Statement stmt = Util.getConnection().createStatement();
            stmt.executeUpdate("DELETE FROM USERS");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
