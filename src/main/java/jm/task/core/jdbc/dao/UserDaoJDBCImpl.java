package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Connection connection = Util.getConnection();
    private String tableName = "User";
    private String createTable = "CREATE TABLE IF NOT EXISTS " + tableName +
            "(id BIGINT PRIMARY KEY AUTO_INCREMENT, " +
            "name VARCHAR(30), lastName VARCHAR(30), age TINYINT)";
    private String dropTable = "DROP TABLE IF EXISTS " + tableName;
    private String saveUser = "INSERT INTO " + tableName + "(name, lastName, age) VALUES (?,?,?)";
    private String deleteUserById = "DELETE FROM " + tableName + " WHERE id=";
    private String getAllFromTable = "SELECT * FROM " + tableName;
    private String cleanTable = "TRUNCATE TABLE " + tableName;

    public UserDaoJDBCImpl() {

    }


    public void createUsersTable() {
        try (Statement statement = connection.createStatement()) {
            System.out.println("Попытка создания таблицы c именем: \"" + tableName + "\"");
            statement.executeUpdate(createTable);
            connection.commit();
            System.out.println("Таблица c именем: \"" + tableName + "\" создана!\n");
        } catch (SQLException e) {
            rollBack();
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement();) {
            System.out.println("Попытка удаления таблицы c именем: \"" + tableName + "\"");
            statement.executeUpdate(dropTable);
            connection.commit();
            System.out.println("Таблица c именем: \"" + tableName + "\" удалена!\n");
        } catch (SQLException e) {
            rollBack();
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(saveUser)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            connection.commit();
            System.out.println("Пользователь " + "\"" + name + "\"" + " добавлен!");
        } catch (SQLException e) {
            rollBack();
            throw new RuntimeException(e);
        }

    }

    public void removeUserById(long id) {
        try (Statement statement = connection.createStatement()) {
            System.out.println("Попытка удаления из таблицы: \"" + tableName + "\" пользователя с id: " + id);
            statement.execute(deleteUserById + id);
            connection.commit();
            System.out.println("Пользователь с id: \"" + id + "\" удален из таблицы:" + "\"" + tableName + "\"\n");
        } catch (SQLException e) {
            rollBack();
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(getAllFromTable);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
                System.out.println(user);
            }
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public void cleanUsersTable() {
        try (Statement statement = connection.createStatement()) {
            System.out.println("Очищение содержимого таблицы..........");
            statement.executeUpdate(cleanTable);
            connection.commit();
            System.out.println("Содержимое таблицы очищено!");
        } catch (SQLException e) {
            rollBack();
            throw new RuntimeException(e);
        }
    }

    public void rollBack() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
