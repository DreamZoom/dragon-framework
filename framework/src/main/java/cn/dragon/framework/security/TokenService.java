package cn.dragon.framework.security;

public interface TokenService {
    Token generate(UserDetails userDetails);
    Token loadToken(String token) throws Exception;
}
