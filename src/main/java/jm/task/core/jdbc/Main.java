package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import static jm.task.core.jdbc.util.Util.closeConnection;
import static jm.task.core.jdbc.util.Util.getConnection;


public class Main {
    static UserServiceImpl userService = new UserServiceImpl();
    public static void main(String[] args) {
        Util util = new Util();
        util.getConnection();
        userService.createUsersTable();
        userService.cleanUsersTable();
        userService.saveUser("Max", "Pasevich", (byte) 21);
        userService.saveUser("Svetlana", "Grizheliyk", (byte) 18);
        userService.saveUser("Ivan", "Ivanov", (byte) 99);
        userService.saveUser("Oleg", "OLegov", (byte) 99);
        userService.getAllUsers();

//        userService.cleanUsersTable();
//        userService.dropUsersTable();





        util.closeConnection();

    }

}
