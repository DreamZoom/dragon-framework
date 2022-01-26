package cn.dragon.framework.security;

import java.util.List;

public interface UserDetails {
    String getId();
    String getUsername();
    boolean isRoot();
    List<String> getPermissions();
}
