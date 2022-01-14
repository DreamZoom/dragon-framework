package cn.dragon.boot.container.web;

import cn.dragon.boot.container.web.method.*;
import org.springframework.context.ApplicationContext;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ServiceHandlerContext implements HandlerContext {

    private HttpServletRequest request;
    private List<HandlerArgumentResolver> argumentResolvers;
    private ApplicationContext applicationContext;
    private Map<Class<?>,Object> attributes;
    private Object returnValue;

    public ServiceHandlerContext(HttpServletRequest request, ApplicationContext applicationContext) {
        this.request = request;
        this.applicationContext = applicationContext;
        this.attributes = new HashMap<>();

        this.returnValue = null;

        this.argumentResolvers = new ArrayList<>();
        this.argumentResolvers.add(new JsonArgumentResolver());
        this.argumentResolvers.add(new MultipartArgumentResolver());
        this.argumentResolvers.add(new DefaultArgumentResolver());
        this.argumentResolvers.add(new AttributeArgumentResolver());
        this.argumentResolvers.add(new BeanArgumentResolver());
    }




    @Override
    public Object getParameter(HandlerParameter parameter) {
        try {
            Object argValue = null;
            for (HandlerArgumentResolver resolver: argumentResolvers) {
                if(!resolver.supportsParameter(parameter)) continue;
                argValue = resolver.resolveArgument(parameter,this);
                if(argValue!=null) break;
            }
            return argValue;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public void setAttribute(Class<?> type, Object value) {
        this.attributes.put(type,value);
    }

    @Override
    public Object getAttribute(Class<?> type) {
        return this.attributes.get(type);
    }

    @Override
    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Override
    public HttpServletRequest getHttpServletRequest() {
        return request;
    }

    @Override
    public Object getReturnValue() {
        return returnValue;
    }

    @Override
    public void setReturnValue(Object returnValue) {
        this.returnValue = returnValue;
    }
}
