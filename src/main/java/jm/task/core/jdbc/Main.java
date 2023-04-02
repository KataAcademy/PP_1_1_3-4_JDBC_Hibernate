package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService service = new UserServiceImpl();
        service.createUsersTable();
        List<User> users = List.of(new User("Ivan", "Petrov", (byte) 21),
                new User("Ivan", "Sidorov", (byte) 22),
                new User("Petr", "Vasichkon", (byte) 30),
                new User("Slava", "Mitkov", (byte) 37));
        users.forEach(user -> {
            service.saveUser(user.getName(), user.getLastName(), user.getAge());
            System.out.printf("User с именем – %s добавлен в базу данных \n", user.getName());
        });
        List<User> fromTable = service.getAllUsers();
        fromTable.forEach(System.out::println);
        service.cleanUsersTable();
        service.dropUsersTable();
    }
}
