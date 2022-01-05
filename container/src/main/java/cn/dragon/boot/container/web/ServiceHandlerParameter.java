package cn.dragon.boot.container.web;

import java.lang.annotation.Annotation;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.util.Objects;

public class ServiceHandlerParameter implements HandlerParameter {

    public ServiceHandlerParameter(Parameter parameter) {
        this.parameter = parameter;
    }

    private Parameter parameter;

    @Override
    public Class getParameterType() {
        return parameter.getType();
    }

    @Override
    public String getParameterName() {
        return parameter.getName();
    }

    @Override
    public <T extends Annotation> T getParameterAnnotation(Class<T> annotationClass) {
        return parameter.getAnnotation(annotationClass);
    }
}
