package cn.dragon.framework.security;

public class AuthenticationUserDetails implements UserDetails {
    private String username;

    public AuthenticationUserDetails(String username) {
        this.username = username;
    }

    @Override
    public String getUsername() {
        return username;
    }
}
