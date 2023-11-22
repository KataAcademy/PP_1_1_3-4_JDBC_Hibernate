package jm.task.core.jdbc;


import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();
        // Создание таблицы User(ов)
        userService.createUsersTable();
        /* Добавление 4 User(ов) в таблицу с данными на свой выбор.
        После каждого добавления должен быть вывод в консоль ( User с именем – name добавлен в базу данных )
         */
        userService.saveUser("User1", "LastName1",(byte) 15);
        userService.saveUser("User3", "LastName3",(byte) 10);
        userService.saveUser("User2", "LastName2",(byte) 33);
        userService.saveUser("User4", "LastName4",(byte) 42);
        // Получение всех User из базы и вывод в консоль ( должен быть переопределен toString в классе User)
        userService.getAllUsers();
        // Очистка таблицы User(ов)
        userService.cleanUsersTable();
        // Удаление таблицы
        userService.dropUsersTable();

    }
}
