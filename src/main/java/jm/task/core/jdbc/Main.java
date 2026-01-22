package jm.task.core.jdbc;
import jm.task.core.jdbc.util.HibernateUtil;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Ivan", "Ivanov", (byte) 29);
        userService.saveUser("Artur", "Miftakhov", (byte) 22);
        userService.saveUser("Ruslan", "Sergeev", (byte) 26);
        userService.saveUser("Max", "Kalashnikov", (byte) 18);
        System.out.println("User с именем — добавлен в базу данных");

        userService.getAllUsers().forEach(System.out::println);

        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
