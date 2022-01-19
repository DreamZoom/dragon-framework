package cn.dragon.cloud.passport.security;

import cn.dragon.framework.security.Token;
import cn.dragon.framework.security.UserDetails;


public class JwtToken implements Token {
    private UserDetails details;
    private String token;
    public JwtToken(String token, UserDetails details) {
        this.token = token;
        this.details = details;
    }

    @Override
    public String getToken() {
        return token;
    }

    public UserDetails getDetails() {
        return details;
    }

}
