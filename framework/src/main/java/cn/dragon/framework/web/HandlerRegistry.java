package cn.dragon.framework.web;

import javax.servlet.http.HttpServletRequest;

public interface HandlerRegistry {
    Handler getHandler(HttpServletRequest request);
    Object getData();
}
