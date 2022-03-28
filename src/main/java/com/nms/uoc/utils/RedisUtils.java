package com.nms.uoc.utils;

import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

//@Component
public class RedisUtils {

    private static RedisUtils redisUtils;

    @PostConstruct
    public void init() {
        redisUtils = this;
//        redisUtils.redisTemplate = this.redisTemplate;
    }

    public static Set<String> keys(String key, StringRedisTemplate redisTemplate) {
        return redisTemplate.keys(key);
    }

    public static Object get(String key, StringRedisTemplate redisTemplate) {
        return redisTemplate.opsForValue().get(key);
    }

    public static void set(String key, String value, StringRedisTemplate redisTemplate) {
        redisTemplate.opsForValue().set(key, value);
    }

    public static void set(String key, String value, Integer expire, StringRedisTemplate redisTemplate) {
        redisTemplate.opsForValue().set(key, value, expire, TimeUnit.SECONDS);
    }

    public static void delete(String key, StringRedisTemplate redisTemplate) {
        redisTemplate.opsForValue().getOperations().delete(key);
    }

    public static void updateExpiration(String key,String hashKey,Object object, Integer expire,  StringRedisTemplate redisTemplate) {
        redisTemplate.opsForHash().put(key, hashKey, object);
        redisTemplate.expire(key, expire, TimeUnit.SECONDS);
    }

    public static void hset(String key, String hashKey, Object object, StringRedisTemplate redisTemplate) {
        redisTemplate.opsForHash().put(key, hashKey, object);
    }

    public static void hset(String key, String hashKey, Object object, Integer expire, StringRedisTemplate redisTemplate) {
        redisTemplate.opsForHash().put(key, hashKey, object);
        redisTemplate.expire(key, expire, TimeUnit.SECONDS);
    }

    public static void hset(String key, Map<String, Object> map, StringRedisTemplate redisTemplate) {
        redisTemplate.opsForHash().putAll(key, map);
    }

    public static void hsetAbsent(String key, String hashKey, Object object, StringRedisTemplate redisTemplate) {
        redisTemplate.opsForHash().putIfAbsent(key, hashKey, object);
    }

    public static String hget(String key, String hashKey, StringRedisTemplate redisTemplate) {
        return redisTemplate.opsForHash().get(key, hashKey).toString();
    }

    public static Object hget(String key, StringRedisTemplate redisTemplate) {
        return redisTemplate.opsForHash().entries(key);
    }

    public static void deleteKey(String key, StringRedisTemplate redisTemplate) {
        redisTemplate.opsForHash().getOperations().delete(key);
    }

    public static Boolean hasKey(String key, StringRedisTemplate redisTemplate) {
        return redisTemplate.opsForHash().getOperations().hasKey(key);
    }

    public static Boolean hasKey(String key, String hasKey, StringRedisTemplate redisTemplate) {
        return redisTemplate.opsForHash().hasKey(key, hasKey);
    }

}
