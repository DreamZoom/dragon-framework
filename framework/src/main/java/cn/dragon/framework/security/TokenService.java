package cn.dragon.framework.security;

public interface TokenService {
    Token generate(UserDetails userDetails);
    UserDetails loadUserDetails(Token token) throws Exception;
    Token verify(Token token) throws Exception;
}
