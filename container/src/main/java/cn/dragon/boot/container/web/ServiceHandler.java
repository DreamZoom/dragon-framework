package cn.dragon.boot.container.web;


import cn.dragon.framework.web.Handler;
import cn.dragon.framework.web.HandlerContext;
import cn.dragon.framework.web.models.ParameterModel;
import org.springframework.core.MethodParameter;
import org.springframework.util.MultiValueMap;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.server.ServerWebExchange;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceHandler extends HandlerMethod implements Handler {


    public ServiceHandler(Object bean, Method method) {
        super(bean, method);
    }


    @Override
    public Object handle(HandlerContext context) throws Throwable {
        MethodParameter[] parameters = getMethodParameters();
        Object[] args = resolveArguments(context,parameters);
        Method method = this.getMethod();
        try {
            return method.invoke(this.getBean(),args);
        }
        catch (InvocationTargetException error){
            throw error.getTargetException();
        }
    }

    @Override
    public ParameterModel[] getApiParameters() {
        MethodParameter[] parameters = getMethodParameters();
        List<ParameterModel> params = new ArrayList<>();
        for (MethodParameter p: parameters) {
            p = p.nestedIfOptional();
            ServiceHandlerParameter parameter = new ServiceHandlerParameter(p);
            params.add(new ParameterModel(parameter.getParameterName(),parameter.getParameterType()));
        }
        return params.toArray(new ParameterModel[params.size()]);
    }


    Object[] resolveArguments(HandlerContext context,MethodParameter[] parameters){
        Object[] args = new Object[parameters.length];
        for (int i = 0; i <parameters.length ; i++) {
            MethodParameter parameter = parameters[i];
            parameter = parameter.nestedIfOptional();
            args[i]=context.getParameter(new ServiceHandlerParameter(parameter));
        }
        return args;
    }

}
