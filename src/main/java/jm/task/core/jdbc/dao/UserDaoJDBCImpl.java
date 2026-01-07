package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    
    public UserDaoJDBCImpl() {
    }

    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users (" +
                     "id BIGINT PRIMARY KEY AUTO_INCREMENT, " +
                     "name VARCHAR(50), " +
                     "last_name VARCHAR(50), " +
                     "age TINYINT)";
        
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(sql);
            System.out.println("Таблица 'users' создана (или уже существует)");
        } catch (SQLException e) {
            System.err.println("Ошибка при создании таблицы: " + e.getMessage());
        }
    }

    @Override
    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS users";
        
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(sql);
            System.out.println("Таблица 'users' удалена (если существовала)");
        } catch (SQLException e) {
            System.err.println("Ошибка при удалении таблицы: " + e.getMessage());
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO users (name, last_name, age) VALUES (?, ?, ?)";
        
        try (Connection connection = Util.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setByte(3, age);
            ps.executeUpdate();
            
            System.out.println("User с именем – " + name + " добавлен в базу данных");
            
        } catch (SQLException e) {
            System.err.println("Ошибка при добавлении пользователя: " + e.getMessage());
        }
    }

    @Override
    public void removeUserById(long id) {
        String sql = "DELETE FROM users WHERE id = ?";
        
        try (Connection connection = Util.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            
            ps.setLong(1, id);
            int rowsDeleted = ps.executeUpdate();
            
            if (rowsDeleted > 0) {
                System.out.println("Пользователь с id=" + id + " удален");
            } else {
                System.out.println("Пользователь с id=" + id + " не найден");
            }
            
        } catch (SQLException e) {
            System.err.println("Ошибка при удалении пользователя: " + e.getMessage());
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT id, name, last_name, age FROM users";
        
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {
            
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setLastName(rs.getString("last_name"));
                user.setAge(rs.getByte("age"));
                users.add(user);
            }
            
            System.out.println("Получено " + users.size() + " пользователей");
            
        } catch (SQLException e) {
            System.err.println("Ошибка при получении пользователей: " + e.getMessage());
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        String sql = "TRUNCATE TABLE users";
        
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(sql);
            System.out.println("Таблица 'users' очищена");
        } catch (SQLException e) {
            System.err.println("Ошибка при очистке таблицы: " + e.getMessage());
        }
    }
}