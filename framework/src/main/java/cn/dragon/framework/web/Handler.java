package cn.dragon.framework.web;

import cn.dragon.framework.web.models.ParameterModel;

import java.lang.reflect.Method;

public interface Handler {
    Method getMethod();

    Object getBean();

    Object handle(HandlerContext context) throws Throwable;

    ParameterModel[] getApiParameters();

}
