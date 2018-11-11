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
            List<Supermercado> result = entityManager.createQuery("xoblou").getResultList();
            System.out.println(result);
            entityManagerFactory.close();
        }catch(Exception e){
            e.printStackTrace();
        } finally{
        }

    }
}
