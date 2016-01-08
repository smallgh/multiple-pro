package com.gaohan.common.service.cache;

import com.alibaba.fastjson.JSON;
import com.gaohan.common.service.cache.redis.RedisCacheManager;
import com.google.common.base.Optional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.Cache;

/**
 * Created by gaohan on 2016/1/6.
 */
public class CacheFetchHelper {

    //个人信息缓存前缀
    public static final String PERSONAL_INFO_PREFIX = "p_info.";


    private RedisCacheManager redisCacheManager;

    public void setRedisCacheManager(RedisCacheManager redisCacheManager) {
        this.redisCacheManager = redisCacheManager;
    }

    public CacheFetchHelper(RedisCacheManager redisCacheManager) {
        this.redisCacheManager = redisCacheManager;
    }

    public <T> T get(String cacheName, String key) {
        Cache.ValueWrapper jsonWrapper = redisCacheManager.getCache(cacheName).get(key);
        if (Optional.fromNullable(jsonWrapper).isPresent()) {
            return (T) jsonWrapper.get();
        }
        return null;
    }

    public <T> T getJson(String cacheName, String key, Class<T> clz) {
        String json = get(cacheName, key);
        if (StringUtils.isNotEmpty(json)) {
            return JSON.parseObject(json, clz);
        }
        return null;
    }
}
