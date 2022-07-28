package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private String createUsersTable = "create table if not exists " +
            "User \n" +
            "(\n" +
            "\tid bigint auto_increment primary key, \n" +
            "\tfirstName varchar(20), \n" +
            "    lastName varchar(20),\n" +
            "\tage tinyint\n" +
            ");";
    private String dropUserTable = "drop table if exists  User;\n";
    private String saveUser = "insert into User (firstName, lastName, age) values (?,?,?)";
    private String removeUserById = "delete from User where id = ";
    private String getAllUsers = "select * from User";
    private String cleamUsersTable = "truncate table User";
    private Connection connection = Util.createConnectionJDBC();

    public UserDaoJDBCImpl() {

    }

    @Override
    public void createUsersTable() {
        try ( Statement statement = connection.createStatement()){
            statement.executeUpdate(createUsersTable);
            connection.commit();
        } catch (SQLException e) {
            rolbackMethod();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void dropUsersTable() {
        try( Statement statement = connection.createStatement()) {
            statement.execute(dropUserTable);
            connection.commit();
        } catch (SQLException e) {
            rolbackMethod();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(saveUser)){
            preparedStatement.setString(1,name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            connection.commit();
            System.out.println("User с именем - " + name + " добавлен в базу данных ");
        } catch (SQLException e) {
            rolbackMethod();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeUserById(long id) {
        try ( Statement statement = connection.createStatement()) {
            statement.execute(removeUserById + id);
            connection.commit();
        } catch (SQLException e) {
            rolbackMethod();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try ( Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(getAllUsers);
            while (resultSet.next()){
                User newUser = new User();
                newUser.setId(resultSet.getLong("id"));
                newUser.setName(resultSet.getString("FirstName"));
                newUser.setLastName(resultSet.getString("lastName"));
                newUser.setAge(resultSet.getByte("age"));
                userList.add(newUser);
                System.out.println(newUser);
            }
            connection.commit();
        } catch (SQLException e) {
            rolbackMethod();
            throw new RuntimeException(e);
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        try ( Statement statement = connection.createStatement()){
            statement.executeUpdate(cleamUsersTable);
            connection.commit();
        } catch (SQLException e) {
            rolbackMethod();
            throw new RuntimeException(e);
        }
    }

    public void rolbackMethod(){
        try {
            connection.rollback();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
