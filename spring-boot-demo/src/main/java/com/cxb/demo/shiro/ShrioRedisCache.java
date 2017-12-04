package com.cxb.demo.shiro;

import com.cxb.demo.DemoApplication;
import com.cxb.demo.utils.SerializeUtils;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.*;

@Slf4j
public class ShrioRedisCache<K, V> implements Cache<K, V> {
	private RedisTemplate<byte[], V> shiroRedisTemplate;
	private String prefix = "shiro_redis:";


	public ShrioRedisCache(String prefix) {
		this.prefix = prefix;
	}

	public void init() {
	    if (this.shiroRedisTemplate == null) {
            RedisTemplate bean = DemoApplication.config.getBean(RedisTemplate.class);
            this.shiroRedisTemplate = bean;
        }
    }

	@Override
	public V get(K key) throws CacheException {
        init();
		if (log.isDebugEnabled()) {
			log.debug("Key: {}", key);
		}
		if (key == null) {
			return null;
		}

		byte[] bkey = getByteKey(key);
		return shiroRedisTemplate.opsForValue().get(bkey);
	}

	@Override
	public V put(K key, V value) throws CacheException {
        init();
		if (log.isDebugEnabled()) {
			log.debug("Key: {}, value: {}", key, value);
		}

		if (key == null || value == null) {
			return null;
		}

		byte[] bkey = getByteKey(key);
		shiroRedisTemplate.opsForValue().set(bkey, value);
		return value;
	}

	@Override
	public V remove(K key) throws CacheException {
        init();
		if (log.isDebugEnabled()) {
			log.debug("Key: {}", key);
		}

		if (key == null) {
			return null;
		}

		byte[] bkey = getByteKey(key);
		ValueOperations<byte[], V> vo = shiroRedisTemplate.opsForValue();
		V value = vo.get(bkey);
		shiroRedisTemplate.delete(bkey);
		return value;
	}

	@Override
	public void clear() throws CacheException {
        init();
		shiroRedisTemplate.getConnectionFactory().getConnection().flushDb();
	}

	@Override
	public int size() {
		Long len = shiroRedisTemplate.getConnectionFactory().getConnection().dbSize();
		return len.intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<K> keys() {
        init();
		byte[] bkey = (prefix + "*").getBytes();
		Set<byte[]> set = shiroRedisTemplate.keys(bkey);
		Set<K> result = Sets.newHashSet();

		if (CollectionUtils.isEmpty(set)) {
			return Collections.emptySet();
		}

		for (byte[] key : set) {
			result.add((K) key);
		}
		return result;
	}

	@Override
	public Collection<V> values() {
        init();
		Set<K> keys = keys();
		List<V> values = new ArrayList<>(keys.size());
		for (K k : keys) {
			byte[] bkey = getByteKey(k);
			values.add(shiroRedisTemplate.opsForValue().get(bkey));
		}
		return values;
	}

	private byte[] getByteKey(K key) {
		if (key instanceof String) {
			String preKey = this.prefix + key;
			return preKey.getBytes();
		} else {
			return SerializeUtils.serialize(key);
		}
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
}