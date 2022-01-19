package cn.dragon.framework.security;

public interface Token {
    String getToken();
    UserDetails getDetails();
}
