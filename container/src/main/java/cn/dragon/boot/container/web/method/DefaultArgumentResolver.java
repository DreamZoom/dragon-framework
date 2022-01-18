package cn.dragon.boot.container.web.method;

import cn.dragon.framework.web.HandlerContext;
import cn.dragon.framework.web.HandlerParameter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.MethodParameter;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class DefaultArgumentResolver implements HandlerArgumentResolver {
    @Override
    public boolean supportsParameter(HandlerParameter parameter) {
        return true;
    }

    @Override
    public Object resolveArgument(HandlerParameter parameter, HandlerContext context) throws Exception {
        String name =parameter.getParameterName();
        Class type = parameter.getParameterType();
        ObjectMapper mapper =new ObjectMapper();

        HttpServletRequest request = context.getHttpServletRequest();

        String valueString = request.getParameter(name);
        if(StringUtils.isEmpty(valueString)) return null;
        return mapper.convertValue(valueString,type);
    }
}
