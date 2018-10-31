package AcessoBancoDeDados;
import org.hibernate.*;

import java.util.List;

public class AcessadorBanco {

    private static SessionFactory factory;

    private void Conectar(){

    }

    public static List SelectGeralLista(String sqlQuery, Class classe){
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

    public static Object SelectGeralObjeto(String sqlQuery, Class classe){
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

    public static void AddOrUpdateOrDelete(String sqlQuery, Class classe){
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
