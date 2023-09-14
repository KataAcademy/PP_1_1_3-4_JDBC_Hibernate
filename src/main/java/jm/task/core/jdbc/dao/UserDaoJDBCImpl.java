package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection con = new Util().getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS User (id BIGINT, name TEXT, lastName TEXT, age TINYINT)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS User";
        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        long nextId;
        createUsersTable(); //Пробуем создать таблицу, если ее не существует
        String sqlGetLastId = "SELECT id FROM User ORDER BY id DESC LIMIT 1"; //
        try (Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery(sqlGetLastId)) {
            if (resultSet.next()) {
                /*Запрашиваем последний ID из таблицы и увеличиваем его на 1,
                если его нет, создаем первый ID*/
                nextId = resultSet.getLong(1) + 1;
            } else {
                nextId = 0L;
            }
            String sqlInsertUser = "INSERT INTO User (id, name, lastName, age) VALUES (?, ?, ?, ?)";
            try (PreparedStatement insertStatement = con.prepareStatement(sqlInsertUser)) {
                insertStatement.setLong(1, nextId);
                insertStatement.setString(2, name);
                insertStatement.setString(3, lastName);
                insertStatement.setByte(4, age);
                //Отправляем данные в таблицу

                insertStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE IGNORE FROM User WHERE ID = ?";
        try (PreparedStatement deleteStatement = con.prepareStatement(sql)) {
            deleteStatement.setLong(1, id);
            deleteStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        String sql = "SELECT id, name, lastName, age FROM User";
        List<User> users = new ArrayList<>();
        try (Statement statement = con.createStatement()){
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge(resultSet.getByte(4));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        String sql = "DELETE IGNORE FROM User";
        try (Statement statement = con.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
