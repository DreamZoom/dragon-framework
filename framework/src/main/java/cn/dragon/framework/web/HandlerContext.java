package cn.dragon.framework.web;


import org.springframework.context.ApplicationContext;

import javax.servlet.http.HttpServletRequest;

public interface HandlerContext {
    Object getParameter(HandlerParameter parameter);

    void setAttribute(Class<?> type,Object value);
    Object getAttribute(Class<?> type);

    ApplicationContext getApplicationContext();

    HttpServletRequest getHttpServletRequest();

    Object getReturnValue();
    void setReturnValue(Object returnValue);
}
