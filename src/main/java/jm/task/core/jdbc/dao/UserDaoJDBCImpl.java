package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.connection;

public class UserDaoJDBCImpl implements UserDao {
    Connection connection = Util.getConnection();
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String sqlNewTable = "create table nameTable " +
                "(id bigint auto_increment primary key, " +
                "name varchar(64) not null, " +
                "lastname varchar(64) not null, " +
                "age tinyint not null);";
        try (Statement statement = connection.createStatement()){
            statement.execute(sqlNewTable);
        } catch (SQLException e) {
        }
    }

    public void dropUsersTable() {
        String sqlDropTable = "drop table nameTable;";
        try (Statement statement = connection.createStatement()) {
            statement.execute(sqlDropTable);
        } catch (SQLException e) {
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        String sqlSaveUser = "insert into nameTable (name, lastname, age) values (?, ?, ?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlSaveUser)){
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        String sqlRemoveUser = String.format("delete from nameTable where id = %d;", id);
        try (Statement statement = connection.createStatement()){
            statement.execute(sqlRemoveUser);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String sqlGetAllUsers = "select * from nameTable";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sqlGetAllUsers);

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAge(resultSet.getByte("age"));

                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() {
        String sqlCleanTable = "truncate table nameTable";
        try (Statement statement = connection.createStatement()) {
            statement.execute(sqlCleanTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
