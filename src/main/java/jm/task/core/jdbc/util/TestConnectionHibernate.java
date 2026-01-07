package jm.task.core.jdbc.util;

import org.hibernate.Session;

public class TestConnectionHibernate {
    public static void main(String[] args) {
        System.out.println("=== Тестируем Hibernate ===");

        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();

            session.createSQLQuery("SELECT 1").getSingleResult();

            session.getTransaction().commit();
            System.out.println("✅ Hibernate работает, Session открывается");
        } catch (Exception e) {
            System.out.println("❌ Hibernate не работает");
            e.printStackTrace();
        }
    }
}
