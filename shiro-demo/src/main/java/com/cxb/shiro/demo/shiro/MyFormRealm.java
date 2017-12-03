package com.cxb.shiro.demo.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.Arrays;
import java.util.stream.Collectors;

public class MyFormRealm extends AuthorizingRealm {

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(token.getPrincipal(), "db-password", "what");
        return info;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // add Permission Resources
        //info.setStringPermissions(userService.findPermissions(username));
        // add Roles String[Set<String> roles]
        if ("admin".equals(username))
            info.setRoles(Arrays.asList("admin", "user").stream().collect(Collectors.toSet()));
        return info;
    }
}
