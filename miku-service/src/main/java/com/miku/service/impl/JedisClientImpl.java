package com.miku.service.impl;

import com.miku.service.IJedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.*;

/**
 * @author: Utopiamiku
 * @date: 2020/9/13 13:26
 * @description:
 */
@Component
public class JedisClientImpl implements IJedisClient {

    @Autowired
    private JedisPool jedisPool;

    private static Jedis jedis;

//    private JedisClientImpl(){
//        if(jedis==null)
//         this.jedis = jedisPool.getResource();
//
//    }

    private Jedis getJedis(){
        if(jedis==null)
         this.jedis = jedisPool.getResource();
        return jedis;
    }

    @Override
    public String get(String key) {
        return getJedis().get(key);
    }

    @Override
    public String set(String key, String value) {
        return getJedis().set(key,value);
    }

    @Override
    public long ttl(String key) {
        return getJedis().ttl(key);
    }

    @Override
    public long expire(String key, int second) {
        return getJedis().expire(key,second);
    }

    @Override
    public long incr(String key) {
        return getJedis().incr(key);
    }

    @Override
    public long hset(String hkey, String key, String value) {
        return getJedis().hset(hkey,key,value);
    }

    @Override
    public String hget(String hkey, String key) {
        return getJedis().hget(hkey,key);
    }

    @Override
    public long del(String key) {
        return getJedis().del(key);
    }

    @Override
    public long hdel(String hkey, String key) {
        return getJedis().hdel(hkey,key);
    }

    @Override
    public Set<String> keys(String k) {
        return getJedis().keys(k);
    }

    @Override
    public Map<String, String> hgetAll(String k) {

        Map<String, String> map = getJedis().hgetAll(k);
//        Collection<String> values = jedis.hgetAll(k).values();
        return  map;
    }
}
