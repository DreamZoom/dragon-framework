package cn.dragon.boot.container.web.method;

import cn.dragon.boot.container.web.HandlerContext;
import cn.dragon.boot.container.web.HandlerParameter;
import org.springframework.core.MethodParameter;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

public interface HandlerArgumentResolver {
    boolean supportsParameter(HandlerParameter parameter);

    @Nullable
    Object resolveArgument(HandlerParameter parameter, HandlerContext context) throws Exception;
}
