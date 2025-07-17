package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl service = new UserServiceImpl();
        service.createUsersTable();


        service.saveUser("Billy", "Johnson", (byte) 37);
        service.saveUser("Max", "Payne", (byte) 27);
        service.saveUser("Bob", "Smith", (byte) 60);
        service.saveUser("Alan", "Wake", (byte) 30);

        List<User> users = service.getAllUsers();
        for (User user : users) {
            System.out.println(user);
        }
        service.cleanUsersTable();
        service.dropUsersTable();

        service.createUsersTable();

        service.saveUser("Name1", "LastName1", (byte) 20);
        service.saveUser("Name2", "LastName2", (byte) 25);
        service.saveUser("Name3", "LastName3", (byte) 31);
        service.saveUser("Name4", "LastName4", (byte) 38);

        service.removeUserById(1);
        service.getAllUsers();
        List<User> users2 = service.getAllUsers();
        for (User user : users2) {
            System.out.println(user);
        }
        service.cleanUsersTable();
        service.dropUsersTable();
    }
}
