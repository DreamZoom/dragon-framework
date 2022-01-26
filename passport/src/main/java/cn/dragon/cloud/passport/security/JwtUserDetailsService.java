package cn.dragon.cloud.passport.security;

import cn.dragon.cloud.passport.domain.Account;
import cn.dragon.cloud.passport.domain.Permission;
import cn.dragon.cloud.passport.service.AccountService;
import cn.dragon.cloud.passport.service.PermissionService;
import cn.dragon.framework.security.UserDetails;
import cn.dragon.framework.security.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Resource
    AccountService accountService;

    @Resource
    PermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws Exception {
        Account account = accountService.findByUsername(username);
        if(account==null) return null;
        List<Permission> permissions = permissionService.queryAccountPermissions(account.getId());
        return new JwtUserDetails(account,permissions);
    }
}
