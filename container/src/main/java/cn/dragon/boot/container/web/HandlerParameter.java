package cn.dragon.boot.container.web;

import java.lang.annotation.Annotation;

public interface HandlerParameter {
    Class getParameterType();
    String getParameterName();
    <T extends Annotation> T getParameterAnnotation(Class<T> annotationClass);
}
