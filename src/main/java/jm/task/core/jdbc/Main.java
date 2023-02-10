package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import java.sql.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        try(Connection conn=Util.getConnection()) {
            UserService service = new UserServiceImpl();
            service.createUsersTable();
            service.saveUser("Phillip", "Sosiskin", (byte) 18);
            service.saveUser("Valeriy", "Zmyshenko", (byte) 54);
            service.saveUser("Albert", "Zmyshenko", (byte) 127);
            service.saveUser("Itsame", "Mario", (byte) 33);
            List<User> list = service.getAllUsers();
            service.cleanUsersTable();
            service.dropUsersTable();
            for (User guy : list) {
                System.out.println(guy);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
