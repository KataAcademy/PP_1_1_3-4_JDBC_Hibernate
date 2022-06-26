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
        try (Connection connection = Util.getMySQLConnection()) {
            String CREATE_TABLE_SQL = "create table if not exists USERS " + "(" +
                    "id int not null AUTO_INCREMENT, " +
                    "name varchar(30) not null, " +
                    "lastname varchar(50) not null, " +
                    "age int, primary key (id)" + ")";
            Statement statement = connection.createStatement();
            statement.executeUpdate(CREATE_TABLE_SQL);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Connection connection = Util.getMySQLConnection()) {
            Statement statement = connection.createStatement();
            String DROP_TABLE = "drop table if exists USERS";
            statement.executeUpdate(DROP_TABLE);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.getMySQLConnection()) {
            String INSERT_USER_DATA = "insert into USERS(name, lastname, age) VALUES (?, ?, ?)";
            PreparedStatement stm = connection.prepareStatement(INSERT_USER_DATA);
            stm.setString(1, name);
            stm.setString(2, lastName);
            stm.setByte(3, age);
            stm.executeUpdate();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = Util.getMySQLConnection()) {
            String DELETE_USER = "delete from USERS where id = " + id;
            Statement statement = connection.createStatement();
            statement.executeUpdate(DELETE_USER);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connection = Util.getMySQLConnection()) {
            String SELECT_ALL_USERS = "select * from USERS";
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastName");
                Byte id = resultSet.getByte("id");
                users.add(new User(name, lastName, id));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        try (Connection connection = Util.getMySQLConnection()) {
            String TRUNCATE_TABLE = "TRUNCATE TABLE kata.USERS";
            Statement statement = connection.createStatement();
            statement.executeUpdate(TRUNCATE_TABLE);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}