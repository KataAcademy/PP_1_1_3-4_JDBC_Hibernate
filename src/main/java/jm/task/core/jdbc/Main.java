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
        userDaoJDBC.saveUser("ex"," n", (byte) 1);
        userDaoJDBC.saveUser("al"," sn", (byte) 12);
        userDaoJDBC.saveUser("a"," ds", (byte) 113);
//        userDaoJDBC.dropUsersTable();
//        userDaoJDBC.cleanUsersTable();
        
        userDaoJDBC.getAllUsers().forEach(System.out::println);



    }
}
