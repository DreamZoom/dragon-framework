package cn.dragon.framework.web;


import org.springframework.context.ApplicationContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface HandlerContext {
    Handler getHandler();
    Object getParameter(HandlerParameter parameter);

    void setAttribute(Class<?> type,Object value);
    Object getAttribute(Class<?> type);

    ApplicationContext getApplicationContext();

    HttpServletRequest getHttpServletRequest();
    HttpServletResponse getHttpServletResponse();

    Object getReturnValue();
    void setReturnValue(Object returnValue);
}
