package AcessoRedis.RedisEntidades;

import Entidades.*;
import java.util.Calendar;
import static AcessoRedis.AcessadorRedis.SelectObjeto;
import static AcessoRedis.AcessadorRedis.Salvar;

public class RedisLotacao {

    /**
     * //pega no Redis o valor de lotacao atual do supermercado
     * @param supermercadoId nome do supermercado
     * @return Retorna objeto com valor de lotacao
     */
    public double PegarLotacaoAtual(double supermercadoId) {
        //transformar entrada no string do Redis
        String busca = supermercadoId + ":" + Calendar.DATE  + ":" + Calendar.HOUR;
        String resultado = SelectObjeto(busca);
        //mapear string recebido em Lotacao
        return 1; //TODO:ARRUMAR ISSO
    }

    /**
     * //salva lotacao atual do supermercado no Redis
     * @param supermercadoId nome do supermercado
     * @param lotacao valor da lotacao atual
     */
    public void PublicaLotacaoAtual(double supermercadoId, double lotacao){
        //salva lotacao atual do supermercado no Redis
        //transformar lotacao nos string do Redis
        String busca = supermercadoId + ":" + Calendar.DATE  + ":" + Calendar.HOUR;
        String objetoEnviado = "[{\"lotacaoAtual\":" + lotacao + ", }]";
        Salvar(busca, String.valueOf(objetoEnviado));
    }

    public void ConsolidaLotacao(Calendar nowDate, String supermercado){
        //consolida lotacao de uma certa hora ou de um dia. Agendado.
        //transformar data e supermercado nos string do Redis
    }

}