package com.company.AcessoBancoDeDados;

import com.company.Entidades.Supermercado;

public class AcessadorBanco {

    private void Conectar(){

    }

    public void selectSupermercadosEmRegiao(double latitude, double longitude){
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Supermercado supermercado = (Supermercado)session.get(Supermercado.class, SupermercadoId);
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
