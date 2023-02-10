package jm.task.core.jdbc.service;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.mapping.PrimaryKey;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    private static final String DELETE_USER="DELETE FROM users WHERE id=?";
    private static final String INSERT_NEW = "INSERT INTO users VALUES(?,?,?,?)";
    private static final String GET_ALL = "SELECT * FROM users";
    @Override
    public void createUsersTable() {
        try(Connection conn = Util.getConnection();
            Statement stat = conn.createStatement()){
            stat.executeUpdate("CREATE TABLE IF NOT EXISTS users (id BIGINT NOT NULL AUTO_INCREMENT , name VARCHAR(30) NOT NULL, last_name VARCHAR(30) NOT NULL, age TINYINT, PRIMARY KEY(id));");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void dropUsersTable() {
        try(Connection conn = Util.getConnection() ;
            Statement stat = conn.createStatement()){
            stat.executeUpdate("drop table IF EXISTS users;");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try(Connection conn = Util.getConnection();
            PreparedStatement stat = conn.prepareStatement(INSERT_NEW)){
            stat.setLong(1,stat.getFetchSize());
            stat.setString(2,name);
            stat.setString(3,lastName);
            stat.setByte(4,age);
            stat.executeUpdate();
            System.out.println("User с именем "+ name +" был добавлен в таблицу");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeUserById(long id) {
        try(Connection conn = Util.getConnection() ;
            PreparedStatement stat = conn.prepareStatement(DELETE_USER)){
            stat.setLong(1,id);
            stat.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try(Connection conn = Util.getConnection() ;
            Statement stat = conn.createStatement()){
            ResultSet res = stat.executeQuery(GET_ALL);
            while(res.next()){
                User user = new User(res.getString("name"),
                        res.getString("last_name"),
                        res.getByte("age"));
                user.setId(res.getLong("id"));
                list.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public void cleanUsersTable() {
        try(Connection conn = Util.getConnection() ;
            Statement stat = conn.createStatement()){
            stat.executeUpdate("TRUNCATE TABLE users");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
