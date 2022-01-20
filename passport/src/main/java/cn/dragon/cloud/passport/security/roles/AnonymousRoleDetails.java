package cn.dragon.cloud.passport.security.roles;

import cn.dragon.framework.security.RoleDetails;
import cn.dragon.framework.web.HandlerContext;

public class AnonymousRoleDetails implements RoleDetails {

    @Override
    public boolean check(HandlerContext context) {
        return false;
    }
}
