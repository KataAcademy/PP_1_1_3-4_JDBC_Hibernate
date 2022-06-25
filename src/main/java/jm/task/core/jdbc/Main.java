package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
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
//        userDaoJDBC.saveUser("alex","bolduin", (byte) 1);
//        userDaoJDBC.saveUser("tony","stark", (byte) 2);
//        userDaoJDBC.saveUser("pen"," penis", (byte) 3);
////        userDaoJDBC.cleanUsersTable();
//        userDaoJDBC.dropUsersTable();
//
//
//        userDaoJDBC.getAllUsers().forEach(System.out::println);
        UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();
        userDaoHibernate.saveUser("Milk","Milkovich", (byte)46);



    }
}
