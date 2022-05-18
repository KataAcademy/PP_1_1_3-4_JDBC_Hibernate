package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private static Connection connection = Util.getBdConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable(){
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users" +
                    "(id BIGINT PRIMARY KEY AUTO_INCREMENT," +
                    "name VARCHAR (10)," +
                    "lastName VARCHAR (20)," +
                    "age TINYINT)");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS users");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users " +
                "(name, lastName, age)" +
                " VALUES (?, ?, ?)")) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        // Установим соединение.
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users" +
                    " WHERE id = ?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {

        List<User> userList = new ArrayList<>();
        User user = new User();

        try (Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM users")) {

            //Через цикл выводим данные.
            while (resultSet.next()) {
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setId(resultSet.getLong("id"));
                user.setAge(resultSet.getByte("age"));
                // Собираем в массив (список).
                userList.add(user);
                // Выводим в консоль данные
                System.out.println(userList);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Возвращаем список пользователей.
        return userList;
    }

    public void cleanUsersTable() {
        // Установим соединение.
        try (Statement statement = connection.createStatement()) {
            // Удаляем все данные коммандой DELETE FROM [имя таблицы]
            statement.executeUpdate("DELETE FROM users");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
