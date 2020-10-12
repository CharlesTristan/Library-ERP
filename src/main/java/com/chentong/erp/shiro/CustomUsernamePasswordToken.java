package com.chentong.erp.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * TODO
 *
 * @author Administrator
 * @version 1.0
 * @date 2020/10/10 16:34
 */
public class CustomUsernamePasswordToken extends UsernamePasswordToken {
    private String token;
    public CustomUsernamePasswordToken(String token) {
        this.token = token;
    }
    @Override
    public Object getPrincipal() {
        return token;
    }
    @Override
    public Object getCredentials() {
        return token;
    }
}
