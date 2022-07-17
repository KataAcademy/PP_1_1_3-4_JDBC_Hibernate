package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl( Main.createDAO());
        userService.createUsersTable();
       // userService.saveUser("Name1", "LastName1", (byte) 20);
       // System.out.println("");
       // userService.saveUser("Name2", "LastName2", (byte) 25);
       // userService.saveUser("Name3", "LastName3", (byte) 31);
       // userService.saveUser("Name4", "LastName4", (byte) 38);

        //userDao.removeUserById(1);
        //userDao.getAllUsers();
        //userDao.cleanUsersTable();
        //userService.dropUsersTable();
    }
    public static UserDao createDAO(){
        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
        return userDaoJDBC;

    }
}
