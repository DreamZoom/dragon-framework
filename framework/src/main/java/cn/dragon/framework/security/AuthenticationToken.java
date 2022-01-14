package cn.dragon.framework.security;

public class AuthenticationToken implements Token {
    public AuthenticationToken(String token) {
        this.token = token;
    }

    private String token;


    @Override
    public String getToken() {
        return token;
    }

    @Override
    public String toString() {
        return token;
    }

}
