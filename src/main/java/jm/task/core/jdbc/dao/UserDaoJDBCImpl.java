package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Connection connection;

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try {
            connection = Util.getInstance().getConnection();
            connection.setAutoCommit(false);
            String CREATE_TABLE_SQL = "create table if not exists USERS " + "(" +
                    "id int not null AUTO_INCREMENT, " +
                    "name varchar(30) not null, " +
                    "lastname varchar(50) not null, " +
                    "age int, primary key (id)" + ")";
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate(CREATE_TABLE_SQL);
            }
            connection.commit();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try {
            connection = Util.getInstance().getConnection();
            connection.setAutoCommit(false);
            String DROP_TABLE = "drop table if exists USERS";
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate(DROP_TABLE);
            }
            connection.commit();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            connection = Util.getInstance().getConnection();
            connection.setAutoCommit(false);
            String INSERT_USER_DATA = "insert into USERS(name, lastname, age) VALUES (?, ?, ?)";
            try (PreparedStatement stm = connection.prepareStatement(INSERT_USER_DATA)) {
                stm.setString(1, name);
                stm.setString(2, lastName);
                stm.setByte(3, age);
                stm.executeUpdate();
            }
            System.out.println("User с именем – " + name + " добавлен в базу данных");
            connection.commit();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try {
            connection = Util.getInstance().getConnection();
            connection.setAutoCommit(false);
            String DELETE_USER = "delete from USERS where id = " + id;
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate(DELETE_USER);
            }
            connection.commit();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            connection = Util.getInstance().getConnection();
            connection.setAutoCommit(false);
            String SELECT_ALL_USERS = "select * from USERS";
            try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String name = resultSet.getString("name");
                    String lastName = resultSet.getString("lastName");
                    Byte age = resultSet.getByte("age");
                    users.add(new User(name, lastName, age));
                }
                preparedStatement.close();
                resultSet.close();
            }
            connection.commit();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        try {
            connection = Util.getInstance().getConnection();
            String TRUNCATE_TABLE = "TRUNCATE TABLE kata.USERS";
            Statement statement = connection.createStatement();
            statement.executeUpdate(TRUNCATE_TABLE);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}