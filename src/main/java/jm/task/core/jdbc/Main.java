package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;

public class Main {
    public static void main(String[] args) {

    //    UserDaoJDBCImpl userDaoJDBC =new UserDaoJDBCImpl();

      //  userDaoJDBC.dropUsersTable();
      //  userDaoJDBC.createUsersTable();
     //   userDaoJDBC.saveUser("Alex", "Ivanov", (byte) 23);
    //    userDaoJDBC.saveUser("Anton", "Petrov", (byte) 35);
     //   userDaoJDBC.saveUser("Elena", "Gromova", (byte) 35);
     //   userDaoJDBC.saveUser("Sergey", "Kim", (byte) 42);
     //   System.out.println(userDaoJDBC.getAllUsers().toString());
       // userDaoJDBC.cleanUsersTable();



        UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();
        //userDaoHibernate.dropUsersTable();
        userDaoHibernate.createUsersTable();
        userDaoHibernate.saveUser("Alex", "Ivanov", (byte) 23);
        userDaoHibernate.saveUser("Anton", "Petrov", (byte) 35);
        userDaoHibernate.saveUser("Elena", "Gromova", (byte) 35);
        userDaoHibernate.saveUser("Sergey", "Kim", (byte) 42);
    //    System.out.println(userDaoHibernate.getAllUsers().toString());




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
