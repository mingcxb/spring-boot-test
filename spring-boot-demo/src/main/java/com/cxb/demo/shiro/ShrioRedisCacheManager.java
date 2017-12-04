package com.cxb.demo.shiro;

import com.cxb.demo.DemoApplication;
import lombok.Setter;
import org.apache.shiro.cache.AbstractCacheManager;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Setter
public class ShrioRedisCacheManager extends AbstractCacheManager {

	public ShrioRedisCacheManager() {
    }

	@Override
	public Cache<byte[], byte[]> createCache(String name) throws CacheException {
		return new ShrioRedisCache<byte[], byte[]>(name);
	}
}
