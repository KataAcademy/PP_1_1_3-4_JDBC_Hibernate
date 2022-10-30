package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import java.util.List;
import jm.task.core.jdbc.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class UserDaoHibernateImpl implements UserDao {

    private static SessionFactory sessionFactory;
    private static Session session;


    static {
            sessionFactory = HibernateUtil.getSessionFactory();
    }
       public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users " +
                "(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(50) NOT NULL, lastName VARCHAR(50) NOT NULL, " +
                "age TINYINT NOT NULL)";
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.createSQLQuery(sql).addEntity(User.class).executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS users";
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.createSQLQuery(sql).addEntity(User.class).executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        session = sessionFactory.getCurrentSession();
        User user = new User(name,lastName,age);
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        System.out.printf("User с именем - %s добавлен в базу данных%n",name);
    }

    @Override
    public void removeUserById(long id) {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        User user = session.get(User.class, id);
        session.delete(user);
        session.getTransaction().commit();
    }

    @Override
    public List<User> getAllUsers() {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<User> users = session.createQuery("from User").getResultList();
        session.getTransaction().commit();
        return users;
    }

    @Override
    public void cleanUsersTable() {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.createQuery("Delete User").executeUpdate();
        session.getTransaction().commit();
    }
}
