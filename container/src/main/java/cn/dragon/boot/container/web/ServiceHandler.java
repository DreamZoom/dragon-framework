package cn.dragon.boot.container.web;

import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class ServiceHandler implements Handler {
    private Object bean;
    private Method method;


    public ServiceHandler(Object bean, Method method) {
        this.bean = bean;
        this.method = method;
    }


    @Override
    public Method getMethod() {
        return this.method;
    }

    @Override
    public Object getBean() {
        return this.bean;
    }

    @Override
    public Object handle(HandlerContext context) throws Exception {
        Parameter[] parameters = getMethodParameters();
        Object[] args = resolveArguments(context,parameters);
        Method method = this.method;
        return method.invoke(this.bean,args);
    }

    Parameter[] getMethodParameters(){
        return this.method.getParameters();
    }

    Object[] resolveArguments(HandlerContext context,Parameter[] parameters){
        Object[] args = new Object[parameters.length];
        for (int i = 0; i <parameters.length ; i++) {
            args[i]=context.getParameter(new ServiceHandlerParameter(parameters[i]));
        }
        return args;
    }

}
