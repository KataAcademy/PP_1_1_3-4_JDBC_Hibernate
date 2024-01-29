package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

public class Main {
    public static void main(String[] args) {

        UserDaoJDBCImpl userDaoJDBC =new UserDaoJDBCImpl();

        userDaoJDBC.dropUsersTable();
        userDaoJDBC.createUsersTable();
        userDaoJDBC.saveUser("Alex", "Ivanov", (byte) 23);
        userDaoJDBC.saveUser("Anton", "Petrov", (byte) 35);
        userDaoJDBC.saveUser("Elena", "Gromova", (byte) 35);
        userDaoJDBC.saveUser("Sergey", "Kim", (byte) 42);
        System.out.println(userDaoJDBC.getAllUsers().toString());
       // userDaoJDBC.cleanUsersTable();



    //    UserServiceImpl userService =new UserServiceImpl();
   //     userService.createUsersTable();

   //     userService.saveUser("Alex", "Ivanov", (byte) 23);
   //     userService.saveUser("Anton", "Petrov", (byte) 35);
   //     userService.saveUser("Elena", "Gromova", (byte) 35);
   //     userService.saveUser("Sergey", "Kim", (byte) 42);

  //      userService.getAllUsers();
   //     userService.cleanUsersTable();
   //     userService.dropUsersTable();

    }
}
