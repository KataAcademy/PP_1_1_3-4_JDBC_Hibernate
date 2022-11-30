package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();

        userService.saveUser("Ivan", "Carevich", (byte) 26);
        userService.saveUser("Vasilisa", "premydraya", (byte) 22);
        userService.saveUser("Olesha", "Popovich", (byte) 31);
        userService.saveUser("Ilya", "Myrometc", (byte) 38);
    }
}
