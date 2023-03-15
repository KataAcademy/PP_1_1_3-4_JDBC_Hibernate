package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
private Util databaseConnection;

public void createUsersTable() {
    try (Connection DBConnect = databaseConnection.getConnectionToDB()) {
        try (Statement statement = DBConnect.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS users" +
                    "(id LONG NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                    " name STRING," +
                    " lastName STRING," +
                    " age BYTE)");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

public void dropUsersTable() {

}

public void saveUser(String name, String lastName, byte age) {

}

public void removeUserById(long id) {

}

public List<User> getAllUsers() {
    return null;
}

public void cleanUsersTable() {

}
}
