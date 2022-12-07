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
        try(Statement statement = getConnection().createStatement()) {
            statement.executeUpdate("create table if not exists User(id bigint not null auto_increment," +
                    " name VARCHAR(30) not null ," +
                    " lastName VARCHAR(30) not null," +
                    " age tinyint, PRIMARY KEY (id))");
        } catch  (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void dropUsersTable() {
        try(Statement statement = getConnection().createStatement()) {
            statement.executeUpdate("drop table if exists User");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
        userDaoJDBC.createUsersTable();

        String insertInfo="INSERT INTO user (name, lastName, age) Values (?, ?,?)";

        try ( PreparedStatement statement = getConnection().prepareStatement
                (insertInfo)) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("User с именем – " + name + " добавлен в базу данных." );
    }

    public void removeUserById(long id) {

        String sqlCommand = "DELETE  FROM  user WHERE id = '"+ id +"'";

        try(Statement statement = getConnection().createStatement()) {
            statement.executeUpdate(sqlCommand);
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
                userList.add(new User(resultSet.getString(2),resultSet.getString(3),
                        resultSet.getByte(4)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println(userList);
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
