package cn.dragon.boot.container.web;

import cn.dragon.framework.web.HandlerParameter;
import org.springframework.core.MethodParameter;

public class ServiceHandlerParameter extends MethodParameter implements HandlerParameter {


    public ServiceHandlerParameter(MethodParameter original) {
        super(original);


    }

    @Override
    public String getParameterName() {
        return this.getParameter().getName();
    }
}
