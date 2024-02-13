package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService  {

  //  UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
   UserDaoHibernateImpl userDaoHibernate =new UserDaoHibernateImpl();
    public void createUsersTable() {

    //   userDaoJDBC.createUsersTable();
        userDaoHibernate.createUsersTable();

        System.out.println("Таблица создана");



    }

    public void dropUsersTable() {

   //     userDaoJDBC.dropUsersTable();
        userDaoHibernate.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {

      //  userDaoJDBC.saveUser(name,lastName,age);
        userDaoHibernate.saveUser(name,lastName,age);


        System.out.println("имя пользователя - " + name + " добавлено в базу" );
    }

    public void removeUserById(long id) {

      //  userDaoJDBC.removeUserById(id);
        userDaoHibernate.removeUserById(id);
    }

    public List<User> getAllUsers() {
      //   System.out.println(userDaoJDBC.getAllUsers().toString());
            System.out.println(userDaoHibernate.getAllUsers().toString());
            return userDaoHibernate.getAllUsers();
       // return    userDaoJDBC.getAllUsers();
    }

    public void cleanUsersTable() {

     //  userDaoJDBC.cleanUsersTable();
        userDaoHibernate.cleanUsersTable();
        System.out.println("Таблица очищена");
    }
}
