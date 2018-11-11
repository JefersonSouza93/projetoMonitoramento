import Entidades.Historico;
import Entidades.Supermercado;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class Main {

    public static void main(String Args[]){

        try{
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.versao.jpa");
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            List<Supermercado> result = entityManager.createNamedQuery("Supermercado.findAll", Supermercado.class).getResultList();
            Supermercado supermercado1 = result.get(0);
            Historico result2= entityManager.createNamedQuery("Historico.findBySupermercadoId", Historico.class).setParameter("supermercadoId", supermercado1.getId()).getSingleResult();
//            List<Historico> result2= entityManager.createNamedQuery("Historico.findAll", Historico.class).getResultList();
            System.out.println(result);
            System.out.println(result2);
            entityManagerFactory.close();
        }catch(Exception e){
            e.printStackTrace();
        } finally{
        }

    }
}
