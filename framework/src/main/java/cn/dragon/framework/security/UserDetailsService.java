package cn.dragon.framework.security;

public interface UserDetailsService {
    UserDetails loadUserByUsername(String username) throws Exception;
}
