package AcessoRedis;

import com.lambdaworks.redis.*;

public class AcessadorRedis {

    private static RedisClient client;
    private static RedisConnection<String, String> connection;


    private static void Conectar() {
        client = new RedisClient(RedisURI);
                //create("redis://password@host:port"));
        connection = client.connect();
    }

    /**
     * Seleciona um objeto unico do Redis.
     * @param search o valor do topico do Redis.
     * @return O string encontrado pelo Redis.
     */
    public static String SelectObjeto(String search){
        Conectar();
        String textObject = connection.get(search);
        Desconectar();
        return textObject;
    }

    /**
     * Salva um string no Redis
     * @param search string de busca
     * @param textObject valor do objeto
     */
    public static void Salvar(String search, String textObject){
        //salva json em uma nova entrada do Redis
        Conectar();
        connection.set(search, textObject);
        Desconectar();
        //map string to object
    }

    private static void Desconectar(){
        connection.close();
        client.shutdown();
    }
}
