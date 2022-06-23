package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static jm.task.core.jdbc.util.Util.getConnection;
public class UserDaoJDBCImpl implements UserDao {
    private Connection connection = getConnection();
    public UserDaoJDBCImpl() {


    }

    public void createUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute("CREATE table if not exists mydb.users(id bigint not null auto_increment, " +
                    "name varchar(30) not null, lastName varchar(30) not null, age tinyint not null, primary key (id))");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute("drop table mydb.users");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

        public void saveUser(String name, String lastName, byte age) {
        try(PreparedStatement preparedStatement = connection.prepareStatement("insert  into mydb.users (name, lastName, age) values (?, ?, ?)")) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3,age);
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void removeUserById(long id) {

    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement("select * from users")) {
           try( ResultSet resultSet = preparedStatement.executeQuery()) {
               while (resultSet.next()) {
                   String name = resultSet.getString("name");
                   String lastName = resultSet.getString("lastName");
                   byte age = resultSet.getByte("age");
                   long id = resultSet.getLong("id");
                   User user = new User(id, age, name, lastName);
                   users.add(user);
               }
           }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        try(Statement statement = connection.createStatement()) {
            statement.execute("TRUNCATE TABLE mydb.users");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
