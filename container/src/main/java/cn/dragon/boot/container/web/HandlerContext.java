package cn.dragon.boot.container.web;

import java.lang.reflect.Parameter;

public interface HandlerContext {
    Object getParameter(String name,Class type);
}
