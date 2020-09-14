package com.miku.service;

import java.util.Map;
import java.util.Set;

/**
 * @author: Utopiamiku
 * @date: 2020/9/13 13:25
 * @description:
 */
public interface IJedisClient {
    String get(String key);

    //
    String set(String key, String value);

    long ttl(String key);

    long expire(String key, int second);

    long incr(String key);

    long hset(String hkey, String key, String value);

    String hget(String hkey, String key);

    long del(String key);

    long hdel(String hkey, String key);

    Set<String> keys(String k);

    Map<String, String> hgetAll(String k);

}
