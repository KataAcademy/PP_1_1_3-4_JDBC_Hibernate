package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/test";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public static Connection createConnectionJDBC()  {
        Connection connection;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return connection;
    }


// private static SessionFactory sessionFactory;
//    private static SessionFactory getConnection(){
//        if (sessionFactory == null) {
//            try {
//                Configuration configuration = new Configuration()
//                        .setProperty(Environment.URL, URL)
//                        .setProperty(Environment.USER, USERNAME)
//                        .setProperty(Environment.PASS, PASSWORD)
//                        .setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect")
//                        .setProperty(Environment.HBM2DDL_AUTO, "create-drop")
//                        .setProperty(Environment.SHOW_SQL, "true")
//                        .setProperty(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread")
//                        .addAnnotatedClass(User.class);
//                ServiceRegistry serviceRegistry = new StandardServiceRegistry() {
//                }
//            } catch {
//            }
//
//        }
//    }
}
