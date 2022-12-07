package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.*;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        try(Statement createTable = getConnection().createStatement()) {
            createTable.executeUpdate("create table if not exists User(id bigint not null auto_increment," +
                    " name VARCHAR(30) not null ," +
                    " lastName VARCHAR(30) not null," +
                    " age tinyint, PRIMARY KEY (id))");
        } catch  (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void dropUsersTable() {
        try(Statement dropUsers = getConnection().createStatement()) {
            dropUsers.executeUpdate("drop table if exists User");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
        userDaoJDBC.createUsersTable();

        String insertInfo="INSERT INTO user (name, lastName, age) Values (?, ?,?)";

        try ( PreparedStatement saveUser = getConnection().prepareStatement(insertInfo)) {
            saveUser.setString(1, name);
            saveUser.setString(2, lastName);
            saveUser.setByte(3, age);
            saveUser.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("User с именем – " + name + " добавлен в базу данных." );
        System.out.println("____________________________________");
    }

    public void removeUserById(long id) {

        String sqlCommand = "DELETE  FROM  user WHERE id = ?";

        try(PreparedStatement removeUser = getConnection().prepareStatement(sqlCommand)) {
            removeUser.setByte(1, (byte) id);
            removeUser.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {

        List<User> userList = new ArrayList<>();
        String sqlCommand = "SELECT * FROM user";

        try(Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sqlCommand)) {
            while (resultSet.next()){
                User user = new User(resultSet.getString(2),resultSet.getString(3),
                        resultSet.getByte(4));
                userList.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return userList;
    }

    public void cleanUsersTable() {
        String sqlCommand = "DELETE  FROM user ";

        try(Statement statement = getConnection().createStatement()) {
            statement.executeUpdate(sqlCommand);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
