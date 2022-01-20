package cn.dragon.framework.security;

public interface UserDetails {
    String getId();
    String getUsername();
    boolean isRoot();
}
