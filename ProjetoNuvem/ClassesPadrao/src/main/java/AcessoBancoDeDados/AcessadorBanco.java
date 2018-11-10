package AcessoBancoDeDados;
import org.hibernate.*;

import java.util.List;
import org.hibernate.cfg.Configuration;

public class AcessadorBanco {

    private static SessionFactory factory;

    public AcessadorBanco(){
        try {
            factory = new Configuration().configure().buildSessionFactory();
            } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed. " + ex);
            ex.printStackTrace();
            }
    }

    public List selectGeralLista(String sqlQuery, Class classe){
        Session session = factory.openSession();
        Transaction tx = null;
        SQLQuery query = session.createSQLQuery(sqlQuery);
        query.addEntity(classe);

        try {
            tx = session.beginTransaction();
            List results = query.list();
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
