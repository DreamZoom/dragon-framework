package cn.dragon.cloud.passport.security;

import cn.dragon.cloud.passport.domain.Account;
import cn.dragon.framework.security.UserDetails;

public class JwtUserDetails implements UserDetails {

    private Account account;

    public JwtUserDetails(Account account) {
        this.account = account;
    }

    @Override
    public String getUsername() {
        return account.getUsername();
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
