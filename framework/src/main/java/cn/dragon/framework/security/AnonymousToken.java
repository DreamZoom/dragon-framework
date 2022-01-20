package cn.dragon.framework.security;

public class AnonymousToken implements Token {
    @Override
    public String getToken() {
        return null;
    }

    @Override
    public UserDetails getDetails() {
        return null;
    }
}
