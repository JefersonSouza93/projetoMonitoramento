import Entidades.Historico;
import Entidades.HistoricoResposta;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import javax.persistence.*;

public class FuncaoGetHistoricoSupermercado implements RequestHandler<RequestClass, ResponseClass>{

    public ResponseClass handleRequest(RequestClass request, Context context){

        try{
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.versao.jpa");
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            TypedQuery<Historico> query = entityManager.createNamedQuery
                    ("Historico.findBySupermercadoId", Historico.class);
            query.setParameter("supermercadoId", request.id);
            Historico result = query.getSingleResult();
            entityManagerFactory.close();

            HistoricoResposta respostaHistorico = MapearResposta(result);
            return new ResponseClass(respostaHistorico);
        }catch(Exception e){
            //prencher com handler adequado
            e.printStackTrace();
        } finally{

        }
        return new ResponseClass();
    }

    private HistoricoResposta MapearResposta(Historico historico){

        HistoricoResposta respostaHistorico = new HistoricoResposta();
        respostaHistorico.setId(historico.getId());
        respostaHistorico.setMediaDiasDaSemana(historico.getMediaDiasDaSemana());
        respostaHistorico.setMediaMeses(historico.getMediaMeses());
        respostaHistorico.setUltimoDia(historico.getUltimoDia());

        return respostaHistorico;
    }

}