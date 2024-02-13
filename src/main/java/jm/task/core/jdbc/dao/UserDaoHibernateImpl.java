package jm.task.core.jdbc.dao;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;


public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    SessionFactory sessionFactory = Util.getSessionFactory();
    Transaction transaction = null;



    @Override
    public void createUsersTable() {


        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            String sql = ("CREATE TABLE IF NOT EXISTS users " +

                    "  ( id INT NOT NULL AUTO_INCREMENT," +

                    "  name VARCHAR(50)," +

                    "  lastName VARCHAR(50)," +

                    "  age INT," +

                    "  PRIMARY KEY (id));");



            session.createNativeQuery(sql, User.class).executeUpdate();


            transaction.commit();


        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }


        }
    }

    @Override
    public void dropUsersTable() {
    //   User user = new User();
        try (Session session =  sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            session.createNativeQuery("DROP TABLE IF EXISTS users", User.class).executeUpdate();


            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        User user = new User();

        try (Session session =  sessionFactory.openSession()) {

            transaction = session.beginTransaction();

           user.setName(name);
           user.setLastName(lastName);
           user.setAge(age);

            session.persist(user);

            session.getTransaction().commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
    @Override
    public void removeUserById(long id) {
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            session.remove(session.get(User.class, id));
            

            session.getTransaction().commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

        }
    }
        @Override
        public List<User> getAllUsers () {

            try (Session session =  sessionFactory.openSession()) {
                transaction = session.beginTransaction();

                List<User> list = session.createNativeQuery("FROM users ", User.class).getResultList();

                transaction.commit();
                return list;
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }


            }

            return null;
        }


        @Override
        public void cleanUsersTable () {
            try (Session session =  sessionFactory.openSession()) {

                transaction = session.beginTransaction();

                User user = new User();
                session.persist(user);

                session.remove(user);


                session.getTransaction().commit();


            }
        }
}
