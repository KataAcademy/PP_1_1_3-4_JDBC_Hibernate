package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static final String CREAT_TABLE_SQL = "CREATE TABLE IF NOT EXISTS users (\n" +
            "  `id` int NOT NULL AUTO_INCREMENT,\n" +
            "  `name` varchar(100) NOT NULL,\n" +
            "  `lastname` varchar(100) NOT NULL,\n" +
            "  `age` int NOT NULL,\n" +
            "  PRIMARY KEY (`id`)\n" +
            ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;";
    private static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS users;";
    private static final String INSERT_NEW_SQL = "INSERT INTO users(name, lastname, age) VALUES(?, ?, ?);";
    private static final String SELECT_ALL_SQL = "SELECT * FROM users;";
    private static final String CLEAN_TABLE_SQL = "TRUNCATE TABLE users;";
    private static final String REMOVE_BY_ID = "DELETE FROM users where id = ?";
    private final Util util;

    public UserDaoJDBCImpl() {
        util = new Util();
    }

    public void createUsersTable() {
        try {
            Connection connection = util.getConnection();
            Statement statement = connection.createStatement();
            statement.execute(CREAT_TABLE_SQL);
            statement.close();
            connection.close();
        } catch (SQLException ignored) {
        }
    }

    public void dropUsersTable() {
        try {
            Connection connection = util.getConnection();
            Statement statement = connection.createStatement();
            statement.execute(DROP_TABLE_SQL);
            statement.close();
            connection.close();
        } catch (SQLException ignored) {

        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            Connection connection = util.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NEW_SQL);

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.execute();
            preparedStatement.close();
            connection.close();

        } catch (SQLException ignored) {

        }
    }

    public void removeUserById(long id) {
        try {
            Connection connection = util.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_BY_ID);

            preparedStatement.setLong(1, id);

            preparedStatement.execute();
            preparedStatement.close();
            connection.close();

        } catch (SQLException ignored) {

        }
    }

    public List<User> getAllUsers() {
        try {
            ArrayList<User> users = new ArrayList<>();
            Connection connection = util.getConnection();
            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery(SELECT_ALL_SQL);
            while (set.next()) {
                users.add(new User(
                        set.getNString(2),
                        set.getNString(3),
                        set.getByte(4)));
            }
            statement.close();
            connection.close();
            return users;
        } catch (SQLException ignored) {

        }
        return null;
    }

    public void cleanUsersTable() {
        try {
            Connection connection = util.getConnection();
            Statement statement = connection.createStatement();
            statement.execute(CLEAN_TABLE_SQL);

            statement.close();
            connection.close();
        } catch (SQLException ignored) {

        }

    }
}
