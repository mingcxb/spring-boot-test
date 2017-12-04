package com.cxb.shiro.demo;

import com.cxb.shiro.demo.shiro.*;
import org.apache.shiro.authz.Authorizer;
import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.event.EventBus;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.session.mgt.ExecutorServiceSessionValidationScheduler;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.SimpleSessionFactory;
import org.apache.shiro.session.mgt.eis.MemorySessionDAO;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroWebFilterConfiguration;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
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

//    @Bean("shiroFilterFactoryBean")
//    public ShiroFilterFactoryBean filterRegistrationBean() {
//        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
//        shiroFilterFactoryBean.setSecurityManager(new DefaultWebSecurityManager());
//        shiroFilterFactoryBean.setSuccessUrl("/");
//        shiroFilterFactoryBean.setLoginUrl("/login");
//
//        Map<String, Filter> filterMap = new HashMap<>();
//        filterMap.put("intf", new InterfaceFilter());
//        shiroFilterFactoryBean.setFilters(filterMap);
//
//        shiroFilterFactoryBean.setFilterChainDefinitionMap(shiroFilterChainDefinition().getFilterChainMap());
//
//        return shiroFilterFactoryBean;
//    }


//    @Bean("sessionManager")
//    @DependsOn("sessionDAO")
//    public SessionManager sessionManager(SessionDAO sessionDAO) {
//        DefaultWebSessionManager defaultSessionManager = new DefaultWebSessionManager();
//        defaultSessionManager.setSessionDAO(sessionDAO);
//        defaultSessionManager.setGlobalSessionTimeout(1800 * 1000);
//        defaultSessionManager.setSessionValidationInterval(defaultSessionManager.getGlobalSessionTimeout() * 2);
//        return defaultSessionManager;
//    }


    @Bean("sessionDAO")
    public SessionDAO sessionDAO() {
        MySessionDao memorySessionDAO = new MySessionDao();
        return memorySessionDAO;
    }

    @Bean("authorizer")
    public Authorizer authorizer() {
        ModularRealmAuthorizer modularRealmAuthorizer = new ModularRealmAuthorizer();
        return modularRealmAuthorizer;
    }

    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
        chainDefinition.addPathDefinition("/logout", "logout");
        chainDefinition.addPathDefinition("/base/**", "anon");
        chainDefinition.addPathDefinition("/css/**", "anon");
        chainDefinition.addPathDefinition("/layer/**", "anon");
        chainDefinition.addPathDefinition("/user/**", "roles[admin]");
        chainDefinition.addPathDefinition("/**", "authc");
        return chainDefinition;
    }

//    @Bean
//    public RolePermissionResolver dd() {
//	    return new MyRolePermissionResolver();
//    }
}
