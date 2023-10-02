package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Павел", "Петухов", (byte) 102);
        System.out.println("User с именем – Павел Петухов добавлен в базу данных");
        userService.saveUser("Алексей", "Паровозов", (byte) 8);
        System.out.println("User с именем – Алексей Паровозов добавлен в базу данных");
        userService.saveUser("Федор", "Межиков", (byte) 127);
        System.out.println("User с именем – Федор Межиков добавлен в базу данных");
        userService.saveUser("Олег", "Пупкин", (byte) 1);
        System.out.println("User с именем – Олег Пупкин добавлен в базу данных");
        List<User> users = userService.getAllUsers();
        for(User user : users){
            System.out.println(user);
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}

