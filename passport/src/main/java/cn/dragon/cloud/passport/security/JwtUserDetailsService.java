package cn.dragon.cloud.passport.security;

import cn.dragon.cloud.passport.domain.Account;
import cn.dragon.cloud.passport.service.AccountService;
import cn.dragon.framework.security.UserDetails;
import cn.dragon.framework.security.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Resource
    AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String username) throws Exception {
        Account account = accountService.findByUsername(username);
        if(account==null) return null;
        return new JwtUserDetails(account);
    }
}
