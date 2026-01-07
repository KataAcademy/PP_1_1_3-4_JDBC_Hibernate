package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        System.out.println("=== 1. СОЗДАНИЕ ТАБЛИЦЫ ===");
        userService.createUsersTable();

        System.out.println("\n=== 2. ДОБАВЛЕНИЕ 4 ПОЛЬЗОВАТЕЛЕЙ ===");
        userService.saveUser("Томас", "Андерсон", (byte) 25);
        userService.saveUser("Доминик", "Торетто", (byte) 30);
        userService.saveUser("Джонни", "Кейдж", (byte) 27);
        userService.saveUser("Брюс", "Уэйн", (byte) 35);

        System.out.println("\n=== 3. ПОЛУЧЕНИЕ ВСЕХ ПОЛЬЗОВАТЕЛЕЙ ===");
        List<User> allUsers = userService.getAllUsers();
        for (User user : allUsers) {
            System.out.println(user);
        }

        System.out.println("\n=== 4. ОЧИСТКА ТАБЛИЦЫ ===");
        userService.cleanUsersTable();

        System.out.println("\n=== 5. УДАЛЕНИЕ ТАБЛИЦЫ ===");
        userService.dropUsersTable();

        System.out.println("\n=== ПРОГРАММА УСПЕШНО ЗАВЕРШЕНА ===");
    }
}