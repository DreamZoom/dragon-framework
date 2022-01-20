package cn.dragon.cloud.passport.security.roles;

import cn.dragon.cloud.passport.domain.Permission;
import cn.dragon.cloud.passport.domain.Role;
import cn.dragon.framework.security.RoleDetails;
import cn.dragon.framework.utils.JsonUtils;
import cn.dragon.framework.web.Handler;
import cn.dragon.framework.web.HandlerContext;

import java.util.List;

public class AuthenticationRoleDetails implements RoleDetails {

    List<Permission> permissions;
    Role role;
    public AuthenticationRoleDetails(Role role,List<Permission> permissions) {
        this.permissions = permissions;
        this.role = role;
    }

    @Override
    public boolean check(HandlerContext context) {
        Handler handler = context.getHandler();
        String id =handler.getId();
        try{
            for (int i = 0; i <permissions.size() ; i++) {
                Permission permission = permissions.get(i);
                String apis = permission.getApis();
                List<String> apiList = (List<String>) JsonUtils.parse(apis,List.class);
                for (int j = 0; j < apiList.size(); j++) {
                    String api = apiList.get(j);
                    if(id.equals(api)){
                        return true;
                    }
                }
            }

        }
        catch (Exception error){
            return false;
        }
        return false;
    }
}
