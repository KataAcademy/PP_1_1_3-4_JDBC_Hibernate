package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//         реализуйте алгоритм здесь
        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
        userDaoJDBC.createUsersTable();
        userDaoJDBC.saveUser("почему"," эти ", (byte) 1);
        userDaoJDBC.saveUser("гребанные"," тесты", (byte) 2);
        userDaoJDBC.saveUser("не"," проходятся", (byte) 3);
//        userDaoJDBC.cleanUsersTable();
//        userDaoJDBC.dropUsersTable();

        
        userDaoJDBC.getAllUsers().forEach(System.out::println);



    }
}
