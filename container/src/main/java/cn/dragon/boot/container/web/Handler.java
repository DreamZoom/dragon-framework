package cn.dragon.boot.container.web;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public interface Handler {
    Method getMethod();

    Object getBean();

    Object handle(HandlerContext context) throws Throwable;

}
