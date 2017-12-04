package com.cxb.shiro.demo.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public class MySessionDao extends AbstractSessionDAO {

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    public RedisTemplate<String, Session> getRedisTemplate() {
        RedisTemplate<String, Session> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Override
    protected Serializable doCreate(Session session) {
        RedisTemplate<String, Session> redisTemplate = getRedisTemplate();
        Serializable sessionId = this.generateSessionId(session);
        this.assignSessionId(session, sessionId);
        log.info(sessionId.toString());
        redisTemplate.opsForValue().set(sessionId.toString(), session);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable id) {
        RedisTemplate<String, Session> redisTemplate = getRedisTemplate();
        log.info(id.toString());
        return  redisTemplate.opsForValue().get(id.toString());
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        RedisTemplate<String, Session> redisTemplate = getRedisTemplate();
        redisTemplate.opsForValue().set(session.getId().toString(), session);
    }

    @Override
    public void delete(Session session) {
        RedisTemplate<String, Session> redisTemplate = getRedisTemplate();
        redisTemplate.delete(session.getId().toString());
    }

    @Override
    public Collection<Session> getActiveSessions() {
        RedisTemplate<String, Session> redisTemplate = getRedisTemplate();
        Set<String> keys = redisTemplate.keys("*");
        Set<Session> collect = keys.parallelStream().map(a -> redisTemplate.opsForValue().get(a)).collect(Collectors.toSet());
        return collect;
    }
}
