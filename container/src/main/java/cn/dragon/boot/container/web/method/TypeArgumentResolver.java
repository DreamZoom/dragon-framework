package cn.dragon.boot.container.web.method;

import cn.dragon.framework.web.HandlerContext;
import cn.dragon.framework.web.HandlerParameter;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class TypeArgumentResolver implements HandlerArgumentResolver {

    DefaultArgumentResolver defaultArgumentResolver =new DefaultArgumentResolver();
    @Override
    public boolean supportsParameter(HandlerParameter parameter) {
        return true;
    }

    @Override
    public Object resolveArgument(HandlerParameter parameter, HandlerContext context) throws Exception {
        Object instance = parameter.getParameterType().newInstance();
        Field[] fields = parameter.getParameterType().getFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];

            HandlerParameter handlerParameter =new HandlerParameter() {
                @Override
                public Class getParameterType() {
                    return field.getType();
                }

                @Override
                public String getParameterName() {
                    return field.getName();
                }

                @Override
                public <T extends Annotation> T getParameterAnnotation(Class<T> annotationClass) {
                    return null;
                }
            };
            Object fieldValue = defaultArgumentResolver.resolveArgument(handlerParameter,context);
            ReflectionUtils.setField(field,instance,fieldValue);
        }
        return instance;
    }
}
