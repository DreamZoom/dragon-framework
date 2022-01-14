package cn.dragon.boot.container.web.method;

import cn.dragon.boot.container.web.HandlerContext;
import cn.dragon.boot.container.web.HandlerParameter;
import org.springframework.context.ApplicationContext;

import javax.servlet.http.HttpServletRequest;

public class BeanArgumentResolver implements HandlerArgumentResolver {
    @Override
    public boolean supportsParameter(HandlerParameter parameter) {
        return true;
    }

    @Override
    public Object resolveArgument(HandlerParameter parameter, HandlerContext context) throws Exception {
        ApplicationContext applicationContext = context.getApplicationContext();
        return applicationContext.getBean(parameter.getParameterType());
    }
}
