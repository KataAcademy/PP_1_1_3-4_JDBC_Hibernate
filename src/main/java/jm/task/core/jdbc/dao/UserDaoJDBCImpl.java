package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try(Connection conn = Util.getConnection(); Statement statement = conn.createStatement()) {
            statement.execute("CREATE TABLE users ( " +
                    "id serial NOT NULL," +
                    "name varchar(100) NOT NULL," +
                    "lastName varchar(100) NOT NULL," +
                    "age numeric NOT NULL)");
        } catch (SQLException | ClassNotFoundException ignored) {

        }
    }

    public void dropUsersTable() {
        try(Connection conn = Util.getConnection(); Statement starement = conn.createStatement()) {
            starement.execute("DROP TABLE users");
        } catch (SQLException | ClassNotFoundException ignored) {

        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String query = "INSERT INTO users (name, lastName, age) VALUES (?,?,?)";
        try(Connection conn = Util.getConnection(); PreparedStatement prep = conn.prepareStatement(query)) {
            prep.setString(1,name);
            prep.setString(2,lastName);
            prep.setByte(3,age);
            prep.executeUpdate();
        } catch (SQLException | ClassNotFoundException ignored) {

        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM users WHERE id=?";
        try(Connection conn = Util.getConnection(); PreparedStatement prep = conn.prepareStatement(sql)) {
            prep.setLong(1, id);
            prep.executeUpdate();
        } catch (SQLException | ClassNotFoundException ignored) {

        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try(Connection conn = Util.getConnection(); Statement statement = conn.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT id, name, lastName, age FROM users");
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setLastName(rs.getString("lastName"));
                user.setAge(rs.getByte("age"));
                list.add(user);
            }
        } catch (SQLException | ClassNotFoundException ignored) {

        }
        return list;
    }

    public void cleanUsersTable() {
        try(Connection conn = Util.getConnection(); Statement statement = conn.createStatement()) {
            statement.execute("DELETE FROM users");
        } catch (SQLException | ClassNotFoundException ignored) {

        }
    }
}
