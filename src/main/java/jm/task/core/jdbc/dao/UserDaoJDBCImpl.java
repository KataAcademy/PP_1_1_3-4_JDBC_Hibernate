package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable(){
        // Установим соединение.
        Connection connection = Util.getBdConnection();
        Statement statement = null;
        // Создаем таблицу используя Statement и комманды SQL.
        try {
            statement = connection.createStatement();
            // После комманды CREATE TABLE, указываем условие IF NOT EXISTS.
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users" +
                    "(id BIGINT PRIMARY KEY AUTO_INCREMENT," +
                    "name VARCHAR (10)," +
                    "lastName VARCHAR (20)," +
                    "age TINYINT)");

            if (statement != null) {
                statement.close();
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        // Установим соединение.
        Connection connection = Util.getBdConnection();
        Statement statement = null;
        // Удалим всю таблицу целиком.
        try {
            statement = connection.createStatement();
            // После комманды DROP TABLE, указываем условие IF EXISTS.
            statement.executeUpdate("DROP TABLE IF EXISTS users");

            if (statement != null) {
                statement.close();
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        // Установим соединение.
        Connection connection = Util.getBdConnection();
        PreparedStatement preparedStatement = null;
        // Добавляем данные в таблицу.
        try {
            /*
            Сначало указываем таблицу в которую будем вносить данные: INSERT INTO [имя-таблицы].
            После используя метод .setString передаем данные.
             */
            preparedStatement = connection.prepareStatement("INSERT INTO users " +
                    "(name, lastName, age)" +
                    " VALUES (?, ?, ?)");
            // Заполним данные
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            // Выводив в консоль.
            System.out.println("User с именем – " + name + " добавлен в базу данных");

            if (preparedStatement != null) {
                preparedStatement.close();
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        // Установим соединение.
        Connection connection = Util.getBdConnection();
        PreparedStatement preparedStatement = null;
        try {
            // Удаляем данные коммандой DELETE FROM [имя таблицы] WHERE [предикат]
            preparedStatement = connection.prepareStatement("DELETE FROM users" +
                    " WHERE id = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

            if (preparedStatement != null) {
                preparedStatement.close();
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        // Установим соединение.
        Connection connection = Util.getBdConnection();
        Statement statement = null;

        List<User> userList = new ArrayList<>();
        User user = new User();

        try {
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");

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

            if (statement != null) {
                statement.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Возвращаем список пользователей.
        return userList;
    }

    public void cleanUsersTable() {
        // Установим соединение.
        Connection connection = Util.getBdConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            // Удаляем все данные коммандой DELETE FROM [имя таблицы]
            statement.executeUpdate("DELETE FROM users");

            if (statement != null) {
                statement.close();
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
