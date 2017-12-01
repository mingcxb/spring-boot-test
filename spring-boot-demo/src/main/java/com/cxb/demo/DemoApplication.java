package com.cxb.demo;

import org.apache.shiro.web.env.EnvironmentLoaderListener;
import org.apache.shiro.web.servlet.ShiroFilter;
import org.pac4j.core.matching.ExcludedPathMatcher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Bean;

import javax.servlet.annotation.WebListener;
import java.util.Arrays;

@SpringBootApplication
@Cacheable
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
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

//    @Bean("excludedPathMatcher")
//    public  ExcludedPathMatcher excludedPathMatcher() {
//        ExcludedPathMatcher excludedPathMatcher = new ExcludedPathMatcher();
//        excludedPathMatcher.setExcludedPatterns(Arrays.asList(
//                "*.js",
//                "*.css",
//                "*.ico",
//                "*.pnc"
//        ));
//
//        return excludedPathMatcher;
//    }
}
