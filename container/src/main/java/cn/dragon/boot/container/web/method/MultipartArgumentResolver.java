package cn.dragon.boot.container.web.method;

import cn.dragon.boot.container.web.HandlerContext;
import cn.dragon.boot.container.web.HandlerParameter;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;

public class MultipartArgumentResolver implements HandlerArgumentResolver {
    @Override
    public boolean supportsParameter(HandlerParameter parameter) {
        return MultipartFile.class.equals(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(HandlerParameter parameter, HandlerContext context) throws Exception {
        String name =parameter.getParameterName();
        Class type = parameter.getParameterType();

        HttpServletRequest request = context.getHttpServletRequest();
        MultipartRequest multipartRequest = WebUtils.getNativeRequest(request, MultipartRequest.class);
        if(multipartRequest!=null){
            return multipartRequest.getFile(name);
        }
        return null;
    }
}
