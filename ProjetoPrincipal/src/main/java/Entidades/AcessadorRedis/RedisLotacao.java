package Entidades.AcessadorRedis;

import java.util.Calendar;

public class RedisLotacao {

    /**
     * //pega no Redis o valor de lotacao atual do supermercado
     * @param supermercadoId nome do supermercado
     * @return Retorna objeto com valor de lotacao
     */
    public double PegarLotacaoAtual(double supermercadoId) {
        //transformar entrada no string do Redis

        String busca = supermercadoId + ":" + Calendar.DATE  + ":" + Calendar.HOUR;
        AcessadorRedis jedis = new AcessadorRedis();
        String resultado = jedis.getSet(busca);
        //mapear string recebido em Lotacao
        double lotacao = Double.parseDouble(resultado);
        return lotacao;
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
        AcessadorRedis jedis = new AcessadorRedis();
        jedis.addSet(busca, objetoEnviado);
    }

    public void ConsolidaLotacao(Calendar nowDate, String supermercado){
        //consolida lotacao de uma certa hora ou de um dia. Agendado.
        //transformar data e supermercado nos string do Redis
    }

}
