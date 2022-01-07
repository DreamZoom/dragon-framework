package cn.dragon.boot.container.web;

import cn.dragon.boot.container.web.models.ParameterModel;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public interface Handler {
    Method getMethod();

    Object getBean();

    Object handle(HandlerContext context) throws Throwable;

    ParameterModel[] getApiParameters();

}
