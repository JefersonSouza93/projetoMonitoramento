package AcessoRedis;

import com.lambdaworks.redis.*;

public class AcessadorRedis {

    private RedisClient client;
    private RedisConnection<String, String> connection;


    private void Conectar() {
        client = new RedisClient(RedisURI);
                //create("redis://password@host:port"));
        connection = client.connect();
    }

    /**
     * Seleciona um objeto unico do Redis.
     * @param search o valor do topico do Redis.
     * @return O string encontrado pelo Redis.
     */
    public Object SelectObjeto(String search){
        Conectar();
        String textObject = connection.get(search);
        Desconectar();
        return textObject;
    }

    /**
     * Salva um string no Redis
     * @param search o valor do topico do Redis.
     */
    public void SalvaInformacoes(String search, String textObject){
        //salva json em uma nova entrada do Redis
        Conectar();
        connection.set(search, textObject);
        Desconectar();
        //map string to object
    }

    private void Desconectar(){
        connection.close();
        client.shutdown();
    }
}
