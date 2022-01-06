package cn.dragon.boot.container.web;

import org.springframework.core.MethodParameter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.util.Objects;

public class ServiceHandlerParameter extends MethodParameter implements HandlerParameter {


    public ServiceHandlerParameter(MethodParameter original) {
        super(original);


    }

    @Override
    public String getParameterName() {
        return this.getParameter().getName();
    }
}
