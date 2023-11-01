package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static final String CREAT_TABLE_SQL = "CREATE TABLE `users` (\n" +
            "  `id` int NOT NULL AUTO_INCREMENT,\n" +
            "  `name` varchar(100) NOT NULL,\n" +
            "  `lastname` varchar(100) NOT NULL,\n" +
            "  `age` int NOT NULL,\n" +
            "  PRIMARY KEY (`id`)\n" +
            ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;";
    private static final String DROP_TABLE_SQL = "DROP TABLE users;";
    private static final String INSERT_NEW_SQL = "INSERT INTO users(name, lastname, age) VALUES(?, ?, ?);";
    private static final String SELECT_ALL_SQL = "SELECT * FROM users;";
    private static final String CLEAN_TABLE_SQL = "TRUNCATE TABLE users;";
    private static final String REMOV_BY_ID = "DELETE FROM users where id = ?";
    private Util util = new Util();
    private Connection connection;

    public UserDaoJDBCImpl() {
        connection = util.getConnection();
    }

    public void createUsersTable() {
        try {
            Statement statement = connection.createStatement();
            statement.execute(CREAT_TABLE_SQL);
        } catch (SQLException e) {
        }
    }

    public void dropUsersTable() {
        try {
            Statement statement = connection.createStatement();
            statement.execute(DROP_TABLE_SQL);
        } catch (SQLException e) {

        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NEW_SQL);

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.execute();

        } catch (SQLException e) {

        }
    }

    public void removeUserById(long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(REMOV_BY_ID);

            preparedStatement.setLong(1, id);

            preparedStatement.execute();
        } catch (SQLException e) {

        }
    }

    public List<User> getAllUsers() {
        try {
            ArrayList<User> users = new ArrayList<>();
            ResultSet set = connection.createStatement().executeQuery(SELECT_ALL_SQL);
            while (set.next()) {
                users.add(new User(
                        set.getNString(2),
                        set.getNString(3),
                        set.getByte(4)));
            }
            return users;
        } catch (SQLException e) {

        }
        return null;
    }

    public void cleanUsersTable() {
        try {
            Statement statement = connection.createStatement();
            statement.execute(CLEAN_TABLE_SQL);
        } catch (SQLException e) {

        }

    }
}
