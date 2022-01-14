package cn.dragon.cloud.passport.security;

import cn.dragon.framework.security.AuthenticationToken;
import cn.dragon.framework.security.UserDetails;

public class JwtToken extends AuthenticationToken {
    private UserDetails details;
    public JwtToken(String token, UserDetails details) {
        super(token);
        this.details = details;
    }
    public UserDetails getDetails() {
        return details;
    }

}
