package com.cxb.demo;

import com.cxb.demo.shiro.ShrioRedisCacheManager;
import com.cxb.demo.shiro.redis.MyRedisProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.env.EnvironmentLoaderListener;
import org.apache.shiro.web.servlet.ShiroFilter;
import org.pac4j.core.matching.ExcludedPathMatcher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import javax.servlet.annotation.WebListener;
import java.lang.reflect.Method;
import java.util.Arrays;

@SpringBootApplication
@EnableCaching
@Slf4j
public class DemoApplication {

    public static ConfigurableApplicationContext config;

	public static void main(String[] args) {
        config = SpringApplication.run(DemoApplication.class, args);
    }

	@Bean
	public EnvironmentLoaderListener listener() {
		return new EnvironmentLoaderListener();
	}

    @Bean
    public  FilterRegistrationBean filter() {
        FilterRegistrationBean bean = new FilterRegistrationBean(new ShiroFilter());
        bean.addUrlPatterns("/*");
        return bean;
    }


    @Bean
    public MyRedisProperties shiroProperties() {
        return new MyRedisProperties();
    }

//    @Bean(name = "shrioRedisCacheManager")
//    @DependsOn(value = "shiroRedisTemplate")
//    public ShrioRedisCacheManager redisCacheManager() {
//        ShrioRedisCacheManager cacheManager = new ShrioRedisCacheManager(shiroRedisTemplate());
//        cacheManager.createCache("shiro_redis:");
//        return cacheManager;
//    }
//
//    @Bean(name = "shiroRedisTemplate")
//    public RedisTemplate<byte[], byte[]> shiroRedisTemplate() {
//        RedisTemplate<byte[], byte[]> template = new RedisTemplate<>();
//        template.setConnectionFactory(connectionFactory());
//        return template;
//    }

}
