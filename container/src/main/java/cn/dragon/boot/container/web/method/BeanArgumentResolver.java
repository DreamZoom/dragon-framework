package cn.dragon.boot.container.web.method;

import cn.dragon.framework.web.HandlerContext;
import cn.dragon.framework.web.HandlerParameter;
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
        try {
            return applicationContext.getBean(parameter.getParameterType());
        }
        catch (Exception err){
            return null;
        }
    }
}
