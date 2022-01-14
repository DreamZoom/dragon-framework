package cn.dragon.cloud.passport.security;

import cn.dragon.cloud.passport.domain.Account;
import cn.dragon.framework.security.AuthenticationUserDetails;

public class JwtUserDetails extends AuthenticationUserDetails {

    private Account account;

    public JwtUserDetails(Account account) {
        super(account.getUsername());
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
