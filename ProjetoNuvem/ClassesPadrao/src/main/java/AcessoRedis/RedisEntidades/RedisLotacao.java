package AcessoRedis.RedisEntidades;

import Entidades.*;

import java.util.Calendar;

public class RedisLotacao {

    public Lotacao PegarLotacaoAtual(String supermercado) {
        //pega no Redis o valor de lotacao atual do supermercado
        //transformar entrada no string do Redis
        //mapear string recebido em Lotacao
        return null;
    }

    public void PublicaLotacaoAtual(double lotacao){
        //salva lotacao atual do supermercado no Redis
        //transformar lotacao nos string do Redis
        //dar get
    }

    public void ConsolidaLotacao(Calendar nowDate, String supermercado){
        //consolida lotacao de uma certa hora ou de um dia. Agendado.
        //transformar data e supermercado nos string do Redis
    }

}