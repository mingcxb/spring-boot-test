package com.cxb.shiro.demo.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.Arrays;
import java.util.stream.Collectors;

public class InterfaceRealm extends AuthorizingRealm {

    public void InterfaceRealm() {
        this.setAuthenticationTokenClass(InterfaceToken.class);
        this.setName("intfRealm");
        this.setCredentialsMatcher(new MyCredentialsMatcher());
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token != null && this.getAuthenticationTokenClass().isAssignableFrom(token.getClass());
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        InterfaceToken token = (InterfaceToken)authenticationToken;
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo();

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
