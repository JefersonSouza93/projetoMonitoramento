package AcessoBancoDeDados;
import Entidades.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class AcessadorBanco {

    private static SessionFactory factory;

    private void Conectar(){

    }

    public void selectSupermercadosEmRegiao(double latitude, double longitude){
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Supermercado supermercado = (Supermercado)session.get(Supermercado.class, 1);
            session.delete(supermercado);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


}
