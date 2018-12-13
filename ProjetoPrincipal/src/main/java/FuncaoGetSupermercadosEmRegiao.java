import Entidades.AcessadorRedis.RedisLotacao;
import Entidades.Historico;
import Entidades.Supermercado;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import javax.persistence.*;
import java.util.List;

public class FuncaoGetSupermercadosEmRegiao implements RequestHandler<RequestClass, ResponseClass>{

    public ResponseClass handleRequest(RequestClass request, Context context){

        try{
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.versao.jpa");
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            TypedQuery<Supermercado> query = entityManager.createNamedQuery
                    ("Supermercado.findByLongitudeAndLatitude", Supermercado.class);
            query.setParameter("latitudeMin", request.latitudeMin);
            query.setParameter("latitudeMax", request.latitudeMax);
            query.setParameter("longitudeMin", request.longitudeMin);
            query.setParameter("longitudeMax", request.longitudeMax);
            List<Supermercado> result = query.getResultList();
            entityManagerFactory.close();

            //PegaLotacaoAtual(result);

            return new ResponseClass(result);
        }catch(Exception e){
            //prencher com handler adequado
            e.printStackTrace();
        } finally{

        }
        return new ResponseClass();
    }

    private void PegaLotacaoAtual(List<Supermercado> supermercadoList){
        //para cada supermercado, acessa Redis e preenche lotacao atual
        RedisLotacao jedis = new RedisLotacao();
        for(int i=0;i<supermercadoList.size();i++){
            double lotacaoAtual = jedis.PegarLotacaoAtual(supermercadoList.get(i).getId());
            supermercadoList.get(i).setLotacaoAtual(lotacaoAtual);
        }
    }
}