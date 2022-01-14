package cn.dragon.framework.security;

public class GuestUserDetails implements UserDetails {

    @Override
    public String getUsername() {
        return "GUEST";
    }
}
