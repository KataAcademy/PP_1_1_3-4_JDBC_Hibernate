package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }


    public void createUsersTable() throws SQLException {
        Connection con = Util.getConnection();
        String sql = "CREATE TABLE IF NOT EXISTS `my_db2`.`users` (\n" +
                "  `id` BIGINT NOT NULL AUTO_INCREMENT,\n" +
                "  `name` VARCHAR(45) NULL,\n" +
                "  `lastName` VARCHAR(45) NULL,\n" +
                "  `age` TINYINT(3) NULL,\n" +
                "  PRIMARY KEY (`id`));";
        Statement stmt = null;
        try {
            stmt = con.createStatement();
            stmt.executeUpdate(sql);
            System.out.println("Table has created");
        } catch (SQLException | NullPointerException e) {
            System.out.println("Database create error");
        } finally {
            if (stmt != null) {
                stmt.close();
            } if (con != null) {
                con.close();
            }
        }
    }

    public void dropUsersTable() throws SQLException {
        Connection con = Util.getConnection();
        Statement stmt = null;
        try {
            stmt = con.createStatement();
            stmt.executeUpdate("drop table IF EXISTS users;");
            System.out.println("Database has deleted successful");
        } catch (SQLException | NullPointerException e){
            System.out.println("Database drop error");
        } finally {
            if (stmt != null) {
                stmt.close();
            } if (con != null) {
                con.close();
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        Connection con = Util.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";
        try {
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.execute();

            System.out.println("User с именем - " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            System.out.println("Ошибка добавления нового пользователя");
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

        public void removeUserById ( long id) throws SQLException {
            Connection con = Util.getConnection();
            PreparedStatement preparedStatement = null;
            String sql = "DELETE FROM users WHERE 'id'=?;";
            try {
                preparedStatement = con.prepareStatement(sql);
                preparedStatement.setLong(1, id);
                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                System.out.println("Remove user error");
                //e.printStackTrace();
            } finally {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (con != null) {
                    con.close();
                }
            }
        }

    public List<User> getAllUsers() throws SQLException {
        Connection con = Util.getConnection();
        List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM users;";
        Statement stmt = null;
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setLastName(rs.getString("lastName"));
                user.setAge(rs.getByte("age"));
                userList.add(user);
            }
            System.out.println("Users got successful");
        } catch (SQLException e) {
            System.out.println("Get all users error");
            //e.printStackTrace();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return userList;
    }

    public void cleanUsersTable() {
        Connection con = Util.getConnection();
        String sql = "TRUNCATE TABLE users;";
        try (Statement stmt = con.createStatement()) {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Database clean error");
        }
    }
}
