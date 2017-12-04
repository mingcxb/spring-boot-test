package com.cxb.shiro.demo;

import com.cxb.shiro.demo.shiro.InterfaceFilter;
import com.cxb.shiro.demo.shiro.InterfaceRealm;
import com.cxb.shiro.demo.shiro.MyCredentialsMatcher;
import com.cxb.shiro.demo.shiro.MyFormRealm;
import org.apache.shiro.authz.Authorizer;
import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import javax.servlet.Filter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
@SpringBootApplication
@EnableCaching
public class ShiroDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShiroDemoApplication.class, args);
	}

	@Bean
	public MyFormRealm formRealm() {
        MyFormRealm realm = new MyFormRealm();
        realm.setCredentialsMatcher(new MyCredentialsMatcher());
        return realm;
    }

    @Bean
    public InterfaceRealm intFealm() {
        InterfaceRealm interfaceRealm = new InterfaceRealm();
        return interfaceRealm;
    }

    @Bean("shiroFilterFactoryBean")
    public ShiroFilterFactoryBean filterRegistrationBean() {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(new DefaultWebSecurityManager());
        shiroFilterFactoryBean.setSuccessUrl("/");
        shiroFilterFactoryBean.setLoginUrl("/login");

        Map<String, Filter> filterMap = new HashMap<>();
        filterMap.put("intf", new InterfaceFilter());
        shiroFilterFactoryBean.setFilters(filterMap);

        shiroFilterFactoryBean.setFilterChainDefinitionMap(shiroFilterChainDefinition().getFilterChainMap());

        return shiroFilterFactoryBean;
    }

    @Bean("authorizer")
    public Authorizer authorizer() {
        ModularRealmAuthorizer modularRealmAuthorizer = new ModularRealmAuthorizer();
        return modularRealmAuthorizer;
    }

    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
        chainDefinition.addPathDefinition("/logout", "logout");
        chainDefinition.addPathDefinition("/base/**", "anon");
        chainDefinition.addPathDefinition("/css/**", "anon");
        chainDefinition.addPathDefinition("/layer/**", "anon");
        chainDefinition.addPathDefinition("/user/**", "intf,roles[admin]");
        chainDefinition.addPathDefinition("/**", "authc");
        return chainDefinition;
    }

//    @Bean
//    public RolePermissionResolver dd() {
//	    return new MyRolePermissionResolver();
//    }
}
