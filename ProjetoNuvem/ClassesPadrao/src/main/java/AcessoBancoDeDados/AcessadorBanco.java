package AcessoBancoDeDados;
import Entidades.Supermercado;
import org.hibernate.*;

import java.util.List;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AcessadorBanco {

    private static SessionFactory factory;
    private static EntityManagerFactory entityManagerFactory;

    public AcessadorBanco(){
        try {
            //factory = new Configuration().configure().buildSessionFactory();
            entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.versao.jpa");
//            new AnnotationConfiguration()
////                    .configure().
////                    addAnnotatedClass(Supermercado.class).
////                    buildSessionFactory();
            } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public List selectGeralLista(String sqlQuery, Class classe){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        SQLQuery query = session.createSQLQuery(sqlQuery);
//        query.addEntity(classe);

        try {
            List results = entityManager.createQuery(sqlQuery).getResultList();
            return results;
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        } finally {
            entityManagerFactory.close();
        }
    }

//    public List selectGeralLista(String sqlQuery, Class classe){
//        Session session = factory.openSession();
//        Transaction tx = null;
//        SQLQuery query = session.createSQLQuery(sqlQuery);
//        query.addEntity(classe);
//
//        try {
//            tx = session.beginTransaction();
//            List results = query.list();
//            session.close();
//            return results;
//        } catch (HibernateException e) {
//            if (tx!=null) tx.rollback();
//            e.printStackTrace();
//        } finally {
//            session.close();
//            return null;
//        }
//    }

    public Object selectGeralObjeto(String sqlQuery, Class classe){
        Session session = factory.openSession();
        Transaction tx = null;
        SQLQuery query = session.createSQLQuery(sqlQuery);
        query.addEntity(classe);

        try {

            tx = session.beginTransaction();
            Object results = query.list();
            session.close();
            return results;
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
            return null;
        }
    }

    public void addOrUpdateOrDelete(String sqlQuery, Class classe){
        Session session = factory.openSession();
        Transaction tx = null;
        SQLQuery query = session.createSQLQuery(sqlQuery);
        query.addEntity(classe);

        try {
            tx = session.beginTransaction();
            query.executeUpdate();
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


}
