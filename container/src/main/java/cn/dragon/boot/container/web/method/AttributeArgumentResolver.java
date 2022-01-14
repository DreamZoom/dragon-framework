package cn.dragon.boot.container.web.method;

import cn.dragon.boot.container.web.HandlerContext;
import cn.dragon.boot.container.web.HandlerParameter;
import org.springframework.context.ApplicationContext;

public class AttributeArgumentResolver implements HandlerArgumentResolver {
    @Override
    public boolean supportsParameter(HandlerParameter parameter) {
        return true;
    }

    @Override
    public Object resolveArgument(HandlerParameter parameter, HandlerContext context) throws Exception {
        return context.getAttribute(parameter.getParameterType());
    }
}
