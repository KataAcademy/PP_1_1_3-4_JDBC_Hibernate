package jm.task.core.jdbc;

import com.mysql.cj.jdbc.ConnectionImpl;
import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.SessionFactory;

import java.lang.module.Configuration;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
//        UserDao userDao = new UserDaoJDBCImpl();
        UserDao userDao2 = new UserDaoHibernateImpl();
//        userDao2.createUsersTable();
//        userDao2.saveUser("Name1", "LastName1", (byte) 20);
//        userDao2.saveUser("Name2", "LastName2", (byte) 25);
//        userDao2.saveUser("Name3", "LastName3", (byte) 31);
//        userDao2.saveUser("Name4", "LastName4", (byte) 38);
        userDao2.removeUserById(1);
//        userDao2.getAllUsers();
//        userDao2.cleanUsersTable();
//        userDao2.dropUsersTable();
    }
}
