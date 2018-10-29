package AcessoBancoDeDados;
import org.hibernate.*;

import java.util.List;

public class AcessadorBanco {

    private static SessionFactory factory;

    private void Conectar(){

    }

    public static List SelectGeral(String sqlQuery){
        Session session = factory.openSession();
        Transaction tx = null;
        SQLQuery query = session.createSQLQuery(sqlQuery);

        try {

            tx = session.beginTransaction();
            List results = query.list();
            return results;
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
            return null;
        }
    }


}
