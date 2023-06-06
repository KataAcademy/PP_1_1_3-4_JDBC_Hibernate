package jm.task.core.jdbc;

import jm.task.core.jdbc.util.Util;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Util util = new Util();
        util.getConnection();
        //  Создание таблицы User(ов)
        // Добавление 4 User(ов) в таблицу с данными на свой выбор. После каждого добавления должен быть вывод в консоль ( User с именем – name добавлен в базу данных )
        // Получение всех User из базы и вывод в консоль ( должен быть переопределен toString в классе User)
        // Очистка таблицы User(ов)
        // Удаление таблицы
    }
}
