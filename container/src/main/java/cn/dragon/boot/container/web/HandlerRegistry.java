package cn.dragon.boot.container.web;

import javax.servlet.http.HttpServletRequest;

public interface HandlerRegistry {
    Handler getHandler(HttpServletRequest request);
    Object getData();
}
