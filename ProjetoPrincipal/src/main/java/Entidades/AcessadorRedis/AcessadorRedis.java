package Entidades.AcessadorRedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisException;

import java.util.Set;

public class AcessadorRedis {

    //address of your redis server
    private static final String redisHost = "lotation.qu9dco.0001.usw2.cache.amazonaws.com";
    private static final Integer redisPort = 6379;
    private static final String redisPassword = "xablau";

    //the jedis connection pool..
    private static JedisPool pool = null;

    public AcessadorRedis() {
        //configure our pool connection
        pool = new JedisPool(new JedisPoolConfig(), redisHost, redisPort, 1000000000);
        //10000, redisPassword);
    }

    public Set<String> getSets(String key){
        Jedis jedis = pool.getResource();
        try {
            //after saving the data, lets retrieve them to be sure that it has really added in redis
            Set members = jedis.smembers(key);
            return members;
        } catch (JedisException e) {
            //if something wrong happen, return it back to the pool
            if (null != jedis) {
                pool.returnBrokenResource(jedis);
                jedis = null;
            }
        } finally {
            ///it's important to return the Jedis instance to the pool once you've finished using it
            if (null != jedis)
                pool.returnResource(jedis);
            return null;
        }
    }

    public String getSet(String key){
        Jedis jedis = pool.getResource();
        try {
            //get objeto
            String objeto = jedis.get(key);
            return objeto;

        } catch (JedisException e) {
            //if something wrong happen, return it back to the pool
            if (null != jedis) {
                pool.returnBrokenResource(jedis);
                jedis = null;
            }
        } finally {
            ///it's important to return the Jedis instance to the pool once you've finished using it
            if (null != jedis)
                pool.returnResource(jedis);
            return null;
        }
    }

    public void addSet(String key, String jsonInformation) {
        //let us first add some data in our redis server using Redis SET.
        //get a jedis connection jedis connection pool
        Jedis jedis = pool.getResource();
        try {
            //save to redis
            jedis.sadd(key, jsonInformation);
        } catch (JedisException e) {
            //if something wrong happen, return it back to the pool
            if (null != jedis) {
                pool.returnBrokenResource(jedis);
                jedis = null;
            }
        } finally {
            ///it's important to return the Jedis instance to the pool once you've finished using it
            if (null != jedis)
                pool.returnResource(jedis);
        }
    }

}

