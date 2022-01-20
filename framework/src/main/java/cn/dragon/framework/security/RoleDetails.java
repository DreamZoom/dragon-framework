package cn.dragon.framework.security;

import cn.dragon.framework.web.HandlerContext;

public interface RoleDetails {
    boolean check(HandlerContext context);
}
