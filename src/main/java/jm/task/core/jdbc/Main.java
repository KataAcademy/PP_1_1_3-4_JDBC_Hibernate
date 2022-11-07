package jm.task.core.jdbc;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import java.sql.SQLException;

public class Main {
    private static final UserService user = new UserServiceImpl();
    public static void main(String[] args) throws SQLException {
         /*
         1. Создание таблицы user'ов
         2. добавление 4 user'ов в таблицу с данными на свой выбор.
         После каждого добавления должен быть вывод в консоль
         (User с именем - name добавлен в базу данных)
         3. Получение всех User из базы и вывод в консоль
         (должен быть переопределен toString в классе User)
         4. Очистка таблицы User'ов
         5. Удаление таблицы*/

        Util util = new Util();
        util.getConnection();

        user.createUsersTable();

        user.saveUser("Lina", "Dolgova", (byte) 30);
        user.saveUser("Ivan", "Mankeev", (byte) 25);
        user.saveUser("Alina", "Galkina", (byte) 21);
        user.saveUser("Timur", "Ivanov", (byte) 32);
        user.removeUserById(2);
        for (User allUsers : user.getAllUsers()) {
            System.out.println(allUsers);
        }

        user.cleanUsersTable();
        user.dropUsersTable();
    }
}
