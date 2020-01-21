package com.fh.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisPool {
    private static JedisPool jedisPool;

    private RedisPool(){

    }
    static {
        JedisPoolConfig jedisPoolConfig=new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(100);//最大连接数
        jedisPoolConfig.setMaxWaitMillis(100*100000);//最大等待时间
        jedisPoolConfig.setMaxIdle(30);//最大空闲连接
        jedisPoolConfig.setTestOnBorrow(true);//检查连接可用性
        jedisPool= new JedisPool(jedisPoolConfig,"192.168.42.146");
    }
    public static  Jedis getJedis(){
        Jedis resource = jedisPool.getResource();
        return  resource;
    }
    public static void returnJedis(Jedis jedis){
        if (jedis!=null){
            jedis.close();
        }
    }
}
