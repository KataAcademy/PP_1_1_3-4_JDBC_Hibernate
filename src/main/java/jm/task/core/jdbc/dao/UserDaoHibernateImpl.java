package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.HibernateUtil;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private static final String CREAT_TABLE_SQL = "CREATE TABLE `users` (\n" +
            "  `id` int NOT NULL AUTO_INCREMENT,\n" +
            "  `name` varchar(100) NOT NULL,\n" +
            "  `lastname` varchar(100) NOT NULL,\n" +
            "  `age` int NOT NULL,\n" +
            "  PRIMARY KEY (`id`)\n" +
            ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;";
    private static final String DROP_TABLE_SQL = "DROP TABLE users;";

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.createSQLQuery(CREAT_TABLE_SQL);
        session.close();
    }

    @Override
    public void dropUsersTable() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.createSQLQuery(DROP_TABLE_SQL);
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(new User(name, lastName, age));
        transaction.commit();
        session.close();

    }

    @Override
    public void removeUserById(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        User user = session.find(User.class, id);
        session.remove(user);
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> rootEntry = cq.from(User.class);
        CriteriaQuery<User> all = cq.select(rootEntry);

        TypedQuery<User> allQuery = session.createQuery(all);
        session.close();
        return allQuery.getResultList();
    }

    @Override
    public void cleanUsersTable() {

    }
}
