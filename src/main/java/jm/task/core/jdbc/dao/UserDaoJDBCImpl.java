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
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void removeUserById(long id) {

    }

    public List<User> getAllUsers() {
        List<User> usersList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from mydb.users");
            while (resultSet.next()) {
                User user = new User();
                user.setAge(resultSet.getByte("age"));
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));

                usersList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
            return usersList;

    }


    public void cleanUsersTable() {
        try(Statement statement = connection.createStatement()) {
            statement.execute("TRUNCATE TABLE mydb.users");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
