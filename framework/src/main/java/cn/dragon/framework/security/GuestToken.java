package cn.dragon.framework.security;

public class GuestToken implements Token {
    @Override
    public String getToken() {
        return null;
    }

    @Override
    public UserDetails getDetails() {
        return null;
    }
}
