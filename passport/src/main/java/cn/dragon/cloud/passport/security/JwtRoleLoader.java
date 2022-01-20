package cn.dragon.cloud.passport.security;

import cn.dragon.cloud.passport.domain.Permission;
import cn.dragon.cloud.passport.domain.Role;
import cn.dragon.cloud.passport.security.roles.AnonymousRoleDetails;
import cn.dragon.cloud.passport.security.roles.AuthenticationRoleDetails;
import cn.dragon.cloud.passport.security.roles.SuperRoleDetails;
import cn.dragon.cloud.passport.service.PermissionService;
import cn.dragon.cloud.passport.service.RoleService;
import cn.dragon.framework.security.AnonymousToken;
import cn.dragon.framework.security.RoleDetails;
import cn.dragon.framework.security.RoleLoader;
import cn.dragon.framework.security.Token;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class JwtRoleLoader implements RoleLoader {

    @Resource
    RoleService roleService;

    @Resource
    PermissionService permissionService;

    @Override
    public List<RoleDetails> load(Token token) throws Exception {
        List<RoleDetails> roles = new ArrayList<>();
        if(token==null || token instanceof AnonymousToken){
            roles.add(new AnonymousRoleDetails());
            return roles;
        }

        if(token.getDetails().isRoot()){
            roles.add(new SuperRoleDetails());
            return roles;
        }
        List<Role> roleList = roleService.queryRoles(token.getDetails().getId());
        for (int i = 0; i < roleList.size() ; i++) {
            Role role = roleList.get(i);
            List<Permission> permissions = permissionService.queryPermissions(role.getId());
            roles.add(new AuthenticationRoleDetails(role,permissions));
        }
        return roles;
    }
}
