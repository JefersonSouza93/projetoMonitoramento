import Entidades.AcessadorRedis.RedisLotacao;
import Entidades.Supermercado;
import Entidades.SupermercadoResposta;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import javax.persistence.*;
import java.util.ArrayList;
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
            try{
                PegaLotacaoAtual(result);
            }
            catch(Exception e){
                e.printStackTrace();
            }
            List<SupermercadoResposta> respostaList = MapearResposta(result);
            return new ResponseClass(respostaList);
        }catch(Exception e){
            //prencher com handler adequado
            e.printStackTrace();
        } finally{

        }
        return new ResponseClass();
    }

    private List<SupermercadoResposta> MapearResposta(List<Supermercado> supermercadoList){
        List<SupermercadoResposta> listResposta = new ArrayList<SupermercadoResposta>();
        for(int i=0;i<supermercadoList.size();i++){
            SupermercadoResposta resposta1 = new SupermercadoResposta();
            resposta1.setId(supermercadoList.get(1).getId());
            resposta1.setLatitude(supermercadoList.get(1).getLatitude());
            resposta1.setLongitude(supermercadoList.get(1).getLongitude());
            resposta1.setRede(supermercadoList.get(1).getRede());
            resposta1.setEndereco(supermercadoList.get(1).getEndereco());
            resposta1.setNome(supermercadoList.get(1).getNome());
            resposta1.setLotacaoMaxima(supermercadoList.get(1).getLotacaoMaxima());
            resposta1.setLotacaoAtual(supermercadoList.get(1).getLotacaoAtual());
            listResposta.add(resposta1);
        }
        return listResposta;
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