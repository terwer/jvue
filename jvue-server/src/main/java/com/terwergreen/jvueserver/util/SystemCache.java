package com.terwergreen.jvueserver.util;

import com.terwergreen.jvueserver.exception.TipException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 数据缓存
 *
 * @author Terwer
 * @since 2017/10/10 16:19
 */
public class SystemCache {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final int MAX_CACHE = 2048;
    private static final SystemCache INSTANCE = new SystemCache();
    private Map<String, Object> cache;

    public static SystemCache instance() {
        return INSTANCE;
    }

    private SystemCache() {
        this(MAX_CACHE);
    }

    private SystemCache(int count) {
        this.cache = new ConcurrentHashMap<>(count);
    }

    /**
     * 获取一个缓存
     *
     * @param key 缓存key
     * @param <T> 泛型
     * @return 数据T
     */
    public <T> T get(String key) {
        return (T) this.cache.get(key);
    }

    /**
     * 获取一个hash类型的缓存
     *
     * @param key   缓存key
     * @param field 缓存field
     * @param <T>   泛型
     * @return 数据T
     */
    public <T> T get(String key, String field) {
        key = key + ":" + field;
        return (T) this.cache.get(key);
    }

    /**
     * 保存一个缓存
     *
     * @param key   缓存key
     * @param value 缓存数据
     */
    public void put(String key, Object value) {
        if (cache.size() > MAX_CACHE) {
          logger.error("The system cache is full");
          throw new TipException("The system cache is full");
        }

        this.cache.put(key, value);
    }

    /**
     * 保存一个hash类型的缓存
     *
     * @param key   缓存key
     * @param field 缓存field
     * @param value 缓存数据
     */
    public void put(String key, String field, Object value) {
        if (cache.size() > MAX_CACHE) {
            logger.error("The system cache is full");
            throw new TipException("The system cache is full");
        }
        key = key + ":" + field;
        this.cache.put(key, value);
    }

    /**
     * 移除一个缓存
     *
     * @param key 缓存key
     */
    public void del(String key) {
        cache.remove(key);
    }

    /**
     * 移除一个hash类型缓存
     *
     * @param key   缓存key
     * @param field 缓存field
     */
    public void del(String key, String field) {
        key = key + ":" + field;
        cache.remove(key);
    }

    /**
     * 清除所有缓存
     */
    public void clean() {
        this.cache.clear();
    }

}
