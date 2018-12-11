import Entidades.AcessadorRedis.RedisLotacao;
import Entidades.Historico;
import Entidades.Supermercado;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class Main {

    public static void main(String Args[]){

        try{
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.versao.jpa");
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            TypedQuery<Supermercado> query = entityManager.createNamedQuery
                    ("Supermercado.findByLongitudeAndLatitude", Supermercado.class);
            query.setParameter("latitudeMin", -23.0);
            query.setParameter("latitudeMax", -23.9);
            query.setParameter("longitudeMin", -45.0);
            query.setParameter("longitudeMax", -45.9);
            List<Supermercado> result = query.getResultList();
            Supermercado supermercado1 = result.get(0);


            RedisLotacao jedis = new RedisLotacao();
            double lotacaoAtual1 = jedis.PegarLotacaoAtual(supermercado1.getId());
            System.out.println(result);
            supermercado1.setLotacaoAtual(lotacaoAtual1);


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
