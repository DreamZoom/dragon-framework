package cn.dragon.cloud.passport.security;

import cn.dragon.cloud.passport.domain.Account;
import cn.dragon.cloud.passport.domain.Permission;
import cn.dragon.framework.security.UserDetails;

import java.util.List;
import java.util.stream.Collectors;

public class JwtUserDetails implements UserDetails {

    private Account account;

    List<Permission> permissions;

    public JwtUserDetails(Account account,List<Permission> permissions) {
        this.account = account;
        this.permissions = permissions;
    }

    @Override
    public String getId() {
        return account.getId();
    }

    @Override
    public String getUsername() {
        return account.getUsername();
    }

    @Override
    public boolean isRoot() {
        return account.getRoot()>0;
    }

    @Override
    public List<String> getPermissions() {
        return permissions.stream().map(m->m.getName()).collect(Collectors.toList());
    }

    public String getPassword(){
        return account.getPassword();
    }

    public String getNickname(){
        return account.getNickname();
    }

    public String getAvatar(){
        return account.getAvatar();
    }

    public String getEmail(){
        return account.getEmail();
    }
}
