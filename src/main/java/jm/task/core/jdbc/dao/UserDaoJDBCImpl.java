package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try {
            Statement statement = Util.getConnection().createStatement();
            String SQL = "CREATE TABLE User (id BIGINT, name VARCHAR(50)," +
                    " lastName VARCHAR (50), age INT)";

            statement.executeUpdate(SQL);
            System.out.println("Table successfully created...");
        } catch (SQLException e) {
            System.out.println("таблица не создана");
            ;
        }
    }

    public void dropUsersTable() {

        try (Statement statement = Util.getConnection().createStatement();
        ) {
            String sql = "DROP TABLE User";
            statement.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        try (PreparedStatement statement = Util.getConnection()
                .prepareStatement("INSERT INTO user VALUES (1, ?, ?, ?);")) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setInt(3, age);

            statement.executeUpdate();
            System.out.printf("User с именем — %s добавлен в базу данных\n", name);

        } catch (SQLException e) {
            System.out.println("Error save User");
        }
    }

    public void removeUserById(long id) {

        try (PreparedStatement preparedStatement
                     = Util.getConnection().prepareStatement("DELETE FROM user WHERE id = ?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String SQL = "SELECT * FROM User";
        try (Statement statement = Util.getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("Id"));
                user.setAge(resultSet.getByte("Age"));
                user.setName(resultSet.getString("Name"));
                user.setLastName(resultSet.getString("LastName"));
                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println("ERROR getAllUser");
        }
        return users;
    }

    public void cleanUsersTable() {
        try (PreparedStatement preparedStatement
                     = Util.getConnection().prepareStatement("TRUNCATE user")) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
