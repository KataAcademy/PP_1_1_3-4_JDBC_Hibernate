package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Connection connection = Util.MySql.getConnection();
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS User ("
                + "id INT AUTO_INCREMENT PRIMARY KEY,"
                + "name VARCHAR(255),"
                + "lastName VARCHAR(255),"
                + "age TINYINT"
                + ")";

        try (Statement statement = connection.createStatement()) {
            statement.execute(createTableSQL);
        } catch (SQLException e) {
            System.out.println("Неудалось создать таблицу \n");
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String createTableSQL = "DROP TABLE User";

        try (Statement statement = connection.createStatement()) {
            statement.execute(createTableSQL);
        } catch (SQLException e) {
            System.out.println("Неудалось удалить таблицу \n");
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String insertSQL = "INSERT INTO User (name, lastName, age) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.printf("Неудалось добавить User'a с именем %s \n", name);
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String deleteSQL = "DELETE FROM User WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.printf("Неудаось удалить User'a с id - %d \n", id);
            e.printStackTrace();
        }
    }

    //TODO: Перенести метод в service и только оттуда его вызывать
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM User";
            ResultSet resultSet =  statement.executeQuery(SQL);


            while (resultSet.next()){
                User user = new User();

                user.setId(resultSet.getLong("id"));
                user.setAge(resultSet.getByte("age"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));

                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println("Неудалось получить всех User'ов \n");
            //throw new RuntimeException(e);
        }
        return users;
    }

    public void cleanUsersTable() {
        String truncateSQL = "TRUNCATE TABLE User";

        try (Statement statement = connection.createStatement()) {
            statement.execute(truncateSQL);
        } catch (SQLException e) {
            System.out.println("Неудалось очистить таблицу \n");
            e.printStackTrace();
        }
    }
}
