package com.gaohan.common.service.cache.redis;

import java.util.Collection;

import org.springframework.cache.Cache;
import org.springframework.cache.support.AbstractCacheManager;

/**
 * Created by gaohan on 2016/1/6.
 */
public class RedisCacheManager extends AbstractCacheManager {

    private Collection<? extends RedisCache> caches;

    @Override
    protected Collection<? extends Cache> loadCaches() {
        return caches;
    }

    public void setCaches(Collection<? extends RedisCache> caches) {
        this.caches = caches;
    }
}
