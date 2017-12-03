package com.cxb.shiro.demo.shiro;

import lombok.Data;
import org.apache.shiro.authc.HostAuthenticationToken;

@Data
public class InterfaceToken implements HostAuthenticationToken {
    private String tokenId;
    private String appId;
    private String hostname;

    public InterfaceToken(String tokenId, String appId, String hostname) {
        this.tokenId = tokenId;
        this.appId = appId;
        this.hostname = hostname;
    }

    public Object getPrincipal() {
        return this.getTokenId();
    }

    public Object getCredentials() {
        return this.getAppId();
    }

    @Override
    public String getHost() {
        return this.getHostname();
    }
}
